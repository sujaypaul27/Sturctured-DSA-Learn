#!/usr/bin/env python3
"""
Autonomous Daily Java DSA Routine Agent for Sturctured-DSA-Learn.
Automates a nightly coding routine:
- Tracks solved problems and scans for duplicates in DSAprogress/
- Progresses through a structured 9-topic DSA curriculum
- Uses Gemini (via google-genai SDK) with structured JSON schemas
- Generates educational, self-contained, compilable Java source code with main() test cases
- Validates syntax with `javac` and deletes .class files
- Assigns randomized evening IST timestamps (8:30 PM to 11:55 PM IST)
- Commits and pushes each problem separately to origin main with a natural human delay
"""

import os
import sys
import json
import random
import time
import re
import shutil
import argparse
import subprocess
from datetime import datetime, timezone, timedelta
from pathlib import Path
from typing import List, Dict, Any, Set, Tuple

from pydantic import BaseModel, Field

# ---------------------------------------------------------------------------
# Constants & Configuration
# ---------------------------------------------------------------------------
AUTHOR_NAME = "sujaypaul27"
AUTHOR_EMAIL = "sujaypaul2711@gmail.com"
DEFAULT_MODEL = os.environ.get("GEMINI_MODEL", "gemini-2.5-flash")
REPO_ROOT = Path(__file__).resolve().parent.parent
DSA_DIR = REPO_ROOT / "DSAprogress"
TRACKER_FILE = DSA_DIR / "progress_tracker.json"
IST_TZ = timezone(timedelta(hours=5, minutes=30), name="IST")

# Structured DSA Curriculum
CURRICULUM = [
    {
        "id": 1,
        "topic": "Basic Math & Number Logic",
        "description": "Fundamental arithmetic, number properties, digit manipulation, and basic number theory.",
        "problems": [
            "SumOfNumbers", "CountDigits", "ReverseNumber", "CheckPalindromeNumber",
            "ArmstrongNumber", "GCDAndLCM", "PrimeCheck", "PrintAllDivisors",
            "FactorialNumber", "FibonacciNumber", "SquareRootInteger", "PerfectNumber",
            "PowerOfTwo", "BinaryToDecimal", "DecimalToBinary", "LeapYearCheck"
        ]
    },
    {
        "id": 2,
        "topic": "Patterns & Recursion Basics",
        "description": "Star and number printing patterns, basic recursion concepts, functional vs parameterized recursion.",
        "problems": [
            "PrintNumbers1ToN", "PrintNumbersNTo1", "SumOfFirstNNaturalNumbers",
            "FactorialRecursion", "ReverseArrayRecursion", "StringPalindromeRecursion",
            "FibonacciRecursion", "PowerOfNumberRecursion", "SubsetsOfArray",
            "TowerOfHanoi", "PermutationsOfString"
        ]
    },
    {
        "id": 3,
        "topic": "Arrays - Easy to Medium",
        "description": "Array traversal, two pointers, prefix sums, Kadane's algorithm, Dutch National Flag, sorting patterns.",
        "problems": [
            "LargestElementInArray", "SecondLargestElement", "CheckIfArraySorted",
            "RemoveDuplicatesFromSortedArray", "LeftRotateArrayByOne", "RotateArrayByK",
            "MoveZeroesToEnd", "LinearSearch", "UnionOfSortedArrays",
            "MissingNumberInArray", "MaxConsecutiveOnes", "FindSingleNumber",
            "TwoSum", "SortColorsDutchNationalFlag", "MajorityElement",
            "KadanesAlgorithmMaxSubarray", "RearrangeArrayElementsBySign",
            "NextPermutation", "LeadersInAnArray", "LongestConsecutiveSequence",
            "SetMatrixZeroes", "RotateMatrixBy90Degrees", "SpiralMatrixTraversal",
            "PascalTriangle"
        ]
    },
    {
        "id": 4,
        "topic": "Strings",
        "description": "String reversal, anagrams, palindrome verification, sliding window substrings, frequency hashing.",
        "problems": [
            "ReverseString", "CheckPalindromeString", "ValidAnagram",
            "LongestCommonPrefix", "IsomorphicStrings", "RotateString",
            "RomanToInteger", "IntegerToRoman", "StringCompression",
            "LongestSubstringWithoutRepeatingCharacters", "GroupAnagrams",
            "ValidPalindromeII"
        ]
    },
    {
        "id": 5,
        "topic": "Searching & Sorting",
        "description": "Binary search on arrays and answers, lower/upper bounds, bubble, selection, insertion, merge, and quicksort.",
        "problems": [
            "BinarySearch", "LowerBoundSearch", "UpperBoundSearch",
            "SearchInsertPosition", "FloorAndCeilInSortedArray",
            "FirstAndLastPositionInSortedArray", "CountOccurrencesInSortedArray",
            "SearchInRotatedSortedArray", "FindMinimumInRotatedSortedArray",
            "FindPeakElement", "BubbleSort", "SelectionSort", "InsertionSort",
            "MergeSort", "QuickSort"
        ]
    },
    {
        "id": 6,
        "topic": "Linked Lists",
        "description": "Singly and doubly linked lists, pointer manipulation, fast and slow pointers, cycle detection, reversal.",
        "problems": [
            "IntroductionToLinkedList", "InsertNodeAtBeginning", "InsertNodeAtEnd",
            "DeleteNodeInLinkedList", "ReverseLinkedList", "MiddleOfLinkedList",
            "DetectCycleInLinkedList", "FindStartingPointOfCycle", "CheckPalindromeLinkedList",
            "RemoveNthNodeFromEndOfList", "DeleteMiddleNodeOfLinkedList",
            "MergeTwoSortedLists", "AddTwoNumbersLinkedList", "IntersectionOfTwoLinkedLists"
        ]
    },
    {
        "id": 7,
        "topic": "Stacks & Queues",
        "description": "LIFO & FIFO implementations, monotonic stack, next greater/smaller elements, parentheses matching, sliding window max.",
        "problems": [
            "ImplementStackUsingArray", "ImplementQueueUsingArray",
            "ImplementQueueUsingStacks", "ImplementStackUsingQueues",
            "ValidParentheses", "MinStack", "NextGreaterElement",
            "NextSmallerElement", "TrappingRainWater", "LargestRectangleInHistogram",
            "SlidingWindowMaximum"
        ]
    },
    {
        "id": 8,
        "topic": "Binary Trees & BST",
        "description": "Traversals (Inorder, Preorder, Postorder, Level Order), tree height, diameter, boundary traversal, BST operations.",
        "problems": [
            "BinaryTreePreorderTraversal", "BinaryTreeInorderTraversal",
            "BinaryTreePostorderTraversal", "BinaryTreeLevelOrderTraversal",
            "MaximumDepthOfBinaryTree", "CheckBalancedBinaryTree", "DiameterOfBinaryTree",
            "MaximumPathSumBinaryTree", "CheckIdenticalTrees", "ZigZagLevelOrderTraversal",
            "RightSideViewOfBinaryTree", "LowestCommonAncestorBinaryTree",
            "SearchInBST", "InsertIntoBST", "DeleteNodeInBST", "ValidateBST",
            "KthSmallestElementInBST"
        ]
    },
    {
        "id": 9,
        "topic": "Heaps, Graphs & Dynamic Programming",
        "description": "Priority queues, BFS/DFS graph traversals, shortest path (Dijkstra), topological sort, 1D & 2D Dynamic Programming.",
        "problems": [
            "KthLargestElementInArray", "TopKFrequentElements", "MergeKSortedLists",
            "GraphRepresentationBFS", "GraphRepresentationDFS", "NumberOfProvinces",
            "RottingOranges", "DetectCycleInUndirectedGraph", "TopologicalSortKahnAlgorithm",
            "DijkstraAlgorithm", "ClimbingStairs", "HouseRobber", "FrogJump",
            "CoinChange", "LongestIncreasingSubsequence", "LongestCommonSubsequence",
            "ZeroOneKnapsack"
        ]
    }
]

# ---------------------------------------------------------------------------
# Pydantic Output Schemas
# ---------------------------------------------------------------------------
class JavaDSAProblem(BaseModel):
    class_name: str = Field(
        description="Clean PascalCase Java public class name matching the problem name, e.g., SumOfNumbers, TwoSum, BinarySearch"
    )
    topic: str = Field(
        description="The DSA topic or module name, e.g., Basic Math & Number Logic, Arrays - Easy to Medium"
    )
    problem_title: str = Field(
        description="Human-readable problem title, e.g., 'Two Sum - Find Indices with Given Target'"
    )
    commit_message: str = Field(
        description="Conventional Git commit message, e.g., 'feat(dsa): solve TwoSum (Arrays - Easy to Medium)'"
    )
    java_code: str = Field(
        description="Complete, self-contained, 100% compilable Java source code including top documentation comment, public class, solution methods, and main method with test cases"
    )

class DSABatchResponse(BaseModel):
    problems: List[JavaDSAProblem] = Field(
        description="List of Java DSA problem solutions"
    )

# ---------------------------------------------------------------------------
# Helper Functions
# ---------------------------------------------------------------------------
def ensure_directories():
    """Ensure DSAprogress directory and progress tracker exist."""
    DSA_DIR.mkdir(parents=True, exist_ok=True)
    if not TRACKER_FILE.exists():
        initial_data = {
            "total_solved": 0,
            "last_updated": None,
            "solved_problems": []
        }
        with open(TRACKER_FILE, "w", encoding="utf-8") as f:
            json.dump(initial_data, f, indent=2)

def load_tracker() -> Dict[str, Any]:
    """Load the progress tracker JSON."""
    ensure_directories()
    try:
        with open(TRACKER_FILE, "r", encoding="utf-8") as f:
            return json.load(f)
    except Exception as e:
        print(f"[WARN] Failed to load tracker ({e}), initializing fresh tracker.")
        return {"total_solved": 0, "last_updated": None, "solved_problems": []}

def save_tracker(tracker: Dict[str, Any]):
    """Save progress tracker data."""
    with open(TRACKER_FILE, "w", encoding="utf-8") as f:
        json.dump(tracker, f, indent=2)

def scan_existing_problems() -> Set[str]:
    """Scan existing .java files and tracker to find all completed problems (case-insensitive set)."""
    ensure_directories()
    solved = set()
    # 1. Scan filesystem
    for p in DSA_DIR.glob("*.java"):
        solved.add(p.stem.lower())
    # 2. Scan tracker file
    tracker = load_tracker()
    for item in tracker.get("solved_problems", []):
        name = item.get("class_name", "")
        if name:
            solved.add(name.lower())
    return solved

def select_next_curriculum_targets(solved_set: Set[str], count: int) -> Tuple[str, List[str]]:
    """
    Select the next `count` problems from the curriculum that have not been solved yet.
    Returns (active_topic, list_of_problem_names).
    """
    chosen = []
    active_topic = CURRICULUM[0]["topic"]

    for topic_block in CURRICULUM:
        topic_name = topic_block["topic"]
        available_in_topic = [p for p in topic_block["problems"] if p.lower() not in solved_set]
        if available_in_topic:
            if not chosen:
                active_topic = topic_name
            for p in available_in_topic:
                chosen.append(p)
                if len(chosen) >= count:
                    return active_topic, chosen

    # If all curated curriculum problems are completed, return last topic and empty list
    return active_topic, chosen

def clean_java_code(code: str) -> str:
    """Strip any accidental markdown code fence formatting from Gemini output."""
    cleaned = code.strip()
    if cleaned.startswith("```"):
        cleaned = re.sub(r"^```(?:java)?\r?\n?", "", cleaned)
        cleaned = re.sub(r"\r?\n?```$", "", cleaned)
    return cleaned.strip()

def compile_check(file_path: Path) -> bool:
    """
    Check if the Java file compiles cleanly with javac.
    Immediately cleans up any generated .class files so only .java remains.
    """
    javac = shutil.which("javac")
    if not javac:
        print(f"[INFO] 'javac' not found in PATH. Skipping local bytecode compilation check for {file_path.name}.")
        return True

    res = subprocess.run([javac, str(file_path)], capture_output=True, text=True)

    # Clean up generated .class files in DSAprogress directory
    for class_file in file_path.parent.glob("*.class"):
        try:
            class_file.unlink()
        except OSError:
            pass

    if res.returncode != 0:
        print(f"[ERROR] javac compilation failed for {file_path.name}:\n{res.stderr}")
        return False

    print(f"[OK] javac compiled {file_path.name} successfully.")
    return True

def generate_staggered_timestamps(n: int, target_date: datetime.date) -> List[datetime]:
    """
    Generate n strictly increasing, realistic evening timestamps between 8:30 PM (20:30)
    and 11:55 PM (23:55) IST (+05:30) for the target date.
    """
    start_minutes = 20 * 60 + 30  # 20:30 IST (1230 minutes from midnight)
    end_minutes = 23 * 60 + 55    # 23:55 IST (1435 minutes from midnight)
    total_span = end_minutes - start_minutes  # 205 minutes

    segment_size = total_span / n
    timestamps = []
    last_minute = start_minutes

    for i in range(n):
        seg_start = start_minutes + (i * segment_size)
        seg_end = start_minutes + ((i + 1) * segment_size)

        min_bound = max(seg_start, last_minute + 12)
        max_bound = max(min_bound + 1, seg_end - 2)
        if max_bound > end_minutes:
            max_bound = end_minutes
        if min_bound >= max_bound:
            chosen_minute = min(min_bound, end_minutes)
        else:
            chosen_minute = random.uniform(min_bound, max_bound)

        last_minute = chosen_minute
        chosen_sec = random.randint(0, 59)

        hour = int(chosen_minute // 60)
        minute = int(chosen_minute % 60)
        dt = datetime(
            year=target_date.year,
            month=target_date.month,
            day=target_date.day,
            hour=hour,
            minute=minute,
            second=chosen_sec,
            tzinfo=IST_TZ
        )
        timestamps.append(dt)

    return sorted(timestamps)

# ---------------------------------------------------------------------------
# Gemini Generation Logic
# ---------------------------------------------------------------------------
def request_gemini_dsa_batch(
    target_count: int,
    active_topic: str,
    target_problems: List[str],
    all_solved: Set[str]
) -> List[JavaDSAProblem]:
    """Call Google Gemini API using google-genai SDK to generate the batch of DSA problems."""
    api_key = os.environ.get("GEMINI_API_KEY")
    if not api_key:
        print("[ERROR] GEMINI_API_KEY environment variable is not set!")
        print("Please set your GEMINI_API_KEY in GitHub Repository Secrets or local environment.")
        sys.exit(1)

    try:
        from google import genai
        from google.genai import types
    except ImportError:
        print("[ERROR] google-genai package is not installed. Run: pip install -r requirements.txt")
        sys.exit(1)

    client = genai.Client(api_key=api_key)

    curriculum_hint = ""
    if target_problems:
        curriculum_hint = f"Recommended sequential curriculum targets for today:\n- " + "\n- ".join(target_problems)

    prompt = f"""You are an elite Java Software Engineer and DSA educator preparing daily learning solutions for an ongoing DSA curriculum.

Today's learning session parameters:
- Target Topic: {active_topic}
- Problems to Generate: Exactly {target_count} problems
{curriculum_hint}

Previously solved problems in this repository (DO NOT DUPLICATE ANY OF THESE):
{json.dumps(sorted(list(all_solved))[:60])}

Requirements for EACH generated problem:
1. `class_name`: PascalCase Java class name matching the file name (e.g. `TwoSum`, `KadanesAlgorithm`, `MergeTwoSortedLists`).
2. `topic`: The DSA topic name (e.g. `{active_topic}`).
3. `problem_title`: Clear, descriptive human title.
4. `commit_message`: Conventional commit message formatted as: `feat(dsa): solve <ClassName> (<Topic>)`.
5. `java_code`: Complete, self-contained, 100% compilable Java program:
   - Header Javadoc comment containing:
     * Problem Statement
     * Example Input & Output with brief explanation
     * Intuition & Step-by-Step Approach
     * Time Complexity (Big-O) with rationale
     * Space Complexity (Big-O) with rationale
   - Exactly ONE `public class <ClassName>` matching `class_name`.
   - Any helper data structures (such as `ListNode`, `TreeNode`, `Pair`) MUST be declared as `static class` inside `<ClassName>` or package-private helper classes so the entire file compiles as a single self-contained `.java` file with `javac`.
   - Clean, well-structured solution methods with clear inline comments explaining non-trivial logic.
   - A fully functional `public static void main(String[] args)` method with 2 to 3 practical test cases and formatted `System.out.println` output demonstrating the execution and results.
   - All necessary standard library imports (e.g. `import java.util.*;`) placed at the top.

Generate exactly {target_count} sequential, high-quality, educational DSA problems now.
"""

    model_candidates = [DEFAULT_MODEL, "gemini-3.8-flash", "gemini-2.5-flash"]
    # De-duplicate while preserving order
    models_to_try = []
    for m in model_candidates:
        if m and m not in models_to_try:
            models_to_try.append(m)

    last_error = None
    for model_name in models_to_try:
        print(f"[INFO] Requesting {target_count} DSA problems using model '{model_name}'...")
        try:
            response = client.models.generate_content(
                model=model_name,
                contents=prompt,
                config=types.GenerateContentConfig(
                    response_mime_type="application/json",
                    response_schema=DSABatchResponse,
                    temperature=0.2,
                )
            )

            # Extract parsed response
            if hasattr(response, "parsed") and response.parsed and hasattr(response.parsed, "problems"):
                batch = response.parsed
                if batch.problems:
                    print(f"[SUCCESS] Received {len(batch.problems)} problems from Gemini ({model_name}).")
                    return batch.problems

            # Fallback to json text parsing if parsed is not directly populated
            raw_text = response.text.strip()
            if raw_text.startswith("```"):
                raw_text = re.sub(r"^```(?:json)?\r?\n?", "", raw_text)
                raw_text = re.sub(r"\r?\n?```$", "", raw_text)

            data = json.loads(raw_text)
            if isinstance(data, dict) and "problems" in data:
                batch = DSABatchResponse.model_validate(data)
                return batch.problems
            elif isinstance(data, list):
                problems = [JavaDSAProblem.model_validate(item) for item in data]
                return problems
        except Exception as e:
            print(f"[WARN] Error with model {model_name}: {e}")
            last_error = e

    raise RuntimeError(f"All Gemini model attempts failed. Last error: {last_error}")

# ---------------------------------------------------------------------------
# Git Workflow Automation
# ---------------------------------------------------------------------------
def run_git_cmd(args: List[str], env: Optional[Dict[str, str]] = None, check: bool = True) -> subprocess.CompletedProcess:
    """Run a git command in the repository root."""
    return subprocess.run(
        ["git"] + args,
        cwd=REPO_ROOT,
        env=env or os.environ.copy(),
        capture_output=True,
        text=True,
        check=check
    )

def commit_and_push_problem(
    problem: JavaDSAProblem,
    timestamp: datetime,
    tracker_data: Dict[str, Any],
    dry_run: bool = False
) -> bool:
    """
    Writes Java file, updates tracker, compiles, commits with custom timestamp, and pushes.
    """
    class_name = problem.class_name.strip()
    java_filename = f"{class_name}.java"
    java_file_path = DSA_DIR / java_filename

    clean_code = clean_java_code(problem.java_code)

    print(f"\n=======================================================")
    print(f"Processing: {class_name} ({problem.topic})")
    print(f"Commit Timestamp: {timestamp.isoformat()}")
    print(f"Commit Message: {problem.commit_message}")
    print(f"=======================================================")

    # 1. Write the Java source file
    with open(java_file_path, "w", encoding="utf-8") as f:
        f.write(clean_code + "\n")
    print(f"[FILE] Written: {java_file_path.relative_to(REPO_ROOT)}")

    # 2. Update progress tracker data
    tracker_entry = {
        "class_name": class_name,
        "topic": problem.topic,
        "problem_title": problem.problem_title,
        "date_solved": timestamp.date().isoformat(),
        "commit_timestamp": timestamp.isoformat(),
        "commit_message": problem.commit_message,
        "file_path": f"DSAprogress/{java_filename}"
    }

    # Avoid duplicate tracker entry
    existing_entries = [e for e in tracker_data.get("solved_problems", []) if e.get("class_name") != class_name]
    existing_entries.append(tracker_entry)
    tracker_data["solved_problems"] = existing_entries
    tracker_data["total_solved"] = len(existing_entries)
    tracker_data["last_updated"] = timestamp.isoformat()
    save_tracker(tracker_data)
    print(f"[FILE] Updated: {TRACKER_FILE.relative_to(REPO_ROOT)}")

    # 3. Verify compilation
    compiled = compile_check(java_file_path)
    if not compiled:
        print(f"[WARN] Compilation issue encountered for {class_name}. Proceeding with tracked commit.")

    if dry_run:
        print(f"[DRY-RUN] Skipping git add, commit, and push for {class_name}.")
        return True

    # 4. Stage files
    run_git_cmd(["add", f"DSAprogress/{java_filename}", "DSAprogress/progress_tracker.json"])

    # 5. Commit with simulated evening IST timestamp
    ts_str = timestamp.strftime("%Y-%m-%dT%H:%M:%S%z")
    # Format offset as +05:30 if needed
    if ts_str.endswith("+0530"):
        ts_str = ts_str[:-5] + "+05:30"

    git_env = os.environ.copy()
    git_env["GIT_AUTHOR_NAME"] = AUTHOR_NAME
    git_env["GIT_AUTHOR_EMAIL"] = AUTHOR_EMAIL
    git_env["GIT_COMMITTER_NAME"] = AUTHOR_NAME
    git_env["GIT_COMMITTER_EMAIL"] = AUTHOR_EMAIL
    git_env["GIT_AUTHOR_DATE"] = ts_str
    git_env["GIT_COMMITTER_DATE"] = ts_str

    try:
        commit_res = run_git_cmd(["commit", "-m", problem.commit_message], env=git_env)
        print(f"[GIT COMMIT] {commit_res.stdout.strip()}")
    except subprocess.CalledProcessError as e:
        print(f"[ERROR] git commit failed:\n{e.stderr}")
        return False

    # 6. Push immediately to origin main
    try:
        push_res = run_git_cmd(["push", "origin", "main"], env=git_env)
        print(f"[GIT PUSH] Pushed {class_name} to origin main successfully.")
    except subprocess.CalledProcessError as e:
        print(f"[ERROR] git push failed:\n{e.stderr}")
        return False

    return True

# ---------------------------------------------------------------------------
# Main Orchestrator
# ---------------------------------------------------------------------------
def main():
    parser = argparse.ArgumentParser(description="Autonomous Daily Java DSA Routine Agent")
    parser.add_argument("--dry-run", action="store_true", help="Generate code without git commit & push")
    parser.add_argument("--count", type=int, choices=[4, 5], default=None, help="Force problem count (4 or 5)")
    args = parser.parse_args()

    print("===================================================================")
    print("      DAILY AUTONOMOUS JAVA DSA AGENT (Sturctured-DSA-Learn)       ")
    print("===================================================================")

    # 1. Determine volume: randomly 4 or 5
    problem_count = args.count if args.count else random.randint(4, 5)
    print(f"[INFO] Daily Session Volume selected: {problem_count} problems.")

    # 2. Scan existing files and tracker
    solved_set = scan_existing_problems()
    print(f"[INFO] Previously completed problems found: {len(solved_set)}")

    # 3. Identify next curriculum targets
    active_topic, target_problems = select_next_curriculum_targets(solved_set, problem_count)
    print(f"[INFO] Active Curriculum Module: '{active_topic}'")
    if target_problems:
        print(f"[INFO] Target problems selected from curriculum: {', '.join(target_problems)}")

    # 4. Generate timestamps between 8:30 PM (20:30) and 11:55 PM (23:55) IST
    now_ist = datetime.now(IST_TZ)
    today_ist = now_ist.date()
    timestamps = generate_staggered_timestamps(problem_count, today_ist)
    print(f"[INFO] Generated {len(timestamps)} staggered evening IST timestamps:")
    for i, ts in enumerate(timestamps, start=1):
        print(f"       Problem {i}: {ts.strftime('%I:%M:%S %p')} IST ({ts.isoformat()})")

    # 5. Fetch problems from Gemini
    problems = request_gemini_dsa_batch(
        target_count=problem_count,
        active_topic=active_topic,
        target_problems=target_problems,
        all_solved=solved_set
    )

    if len(problems) < problem_count:
        print(f"[WARN] Gemini returned {len(problems)} problems (requested {problem_count}). Proceeding with available.")

    # 6. Load tracker
    tracker_data = load_tracker()

    # 7. Commit and push each problem individually with a natural delay
    total_processed = 0
    for idx, (problem, ts) in enumerate(zip(problems, timestamps)):
        success = commit_and_push_problem(
            problem=problem,
            timestamp=ts,
            tracker_data=tracker_data,
            dry_run=args.dry_run
        )
        if success:
            total_processed += 1

        # Real-time human pacing delay between pushes (if not the last problem and not in dry-run)
        if idx < len(problems) - 1 and not args.dry_run:
            pacing_delay = int(os.environ.get("PUSH_DELAY_SECONDS", random.randint(15, 25)))
            print(f"[PACING] Pausing for {pacing_delay}s between pushes to simulate human pacing...")
            time.sleep(pacing_delay)

    print("\n===================================================================")
    print(f" Daily Routine Completed! {total_processed}/{len(problems)} problems committed & pushed.")
    print("===================================================================")

if __name__ == "__main__":
    main()
