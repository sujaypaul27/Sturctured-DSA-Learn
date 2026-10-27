#!/usr/bin/env python3
"""
Personal Daily Java DSA Practice & Sync Utility.
Maintains daily problem-solving routine:
- Tracks solved topics and problems in DSAprogress/
- Follows structured DSA learning curriculum
- Drafts educational, self-contained Java solutions with full test cases
- Verifies syntax via javac
- Commits and pushes each solution with evening timestamps
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
from typing import List, Dict, Any, Set, Tuple, Optional

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
        "description": "Recursion fundamentals, functional vs parameterized recursion, subsets and backtracking.",
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
        "description": "Two pointers, sliding window, prefix sums, Kadane's algorithm, Dutch National Flag.",
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
        "description": "String reversal, anagrams, palindrome verification, substring sliding window.",
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
        "description": "Binary search variants, lower/upper bounds, merge sort, quicksort, rotated arrays.",
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
        "description": "Pointer manipulation, fast & slow pointers, cycle detection, list reversal and merging.",
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
        "description": "Monotonic stacks, next greater/smaller elements, parentheses matching, sliding window maximum.",
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
        "description": "Tree traversals, tree depth & diameter, balanced trees, BST lookups, inserts and deletes.",
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
        "description": "Priority queues, BFS/DFS graph traversals, shortest path, 1D & 2D Dynamic Programming.",
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
# Pydantic Schemas
# ---------------------------------------------------------------------------
class JavaDSAProblem(BaseModel):
    class_name: str = Field(
        description="Clean PascalCase Java public class name matching the problem, e.g., SumOfNumbers, TwoSum, BinarySearch"
    )
    topic: str = Field(
        description="The DSA topic name, e.g., Basic Math & Number Logic, Arrays"
    )
    problem_title: str = Field(
        description="Clear title of the problem, e.g., 'Two Sum - Find Indices with Given Target'"
    )
    commit_message: str = Field(
        description="Clean, natural commit message, e.g., 'feat: solve TwoSum with test cases (Arrays)'"
    )
    java_code: str = Field(
        description="Complete, self-contained, 100% compilable Java source code including header documentation, public class, methods, and main method with test cases"
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
        print(f"[WARN] Initializing fresh tracker ({e}).")
        return {"total_solved": 0, "last_updated": None, "solved_problems": []}

def save_tracker(tracker: Dict[str, Any]):
    """Save progress tracker data."""
    with open(TRACKER_FILE, "w", encoding="utf-8") as f:
        json.dump(tracker, f, indent=2)

def scan_existing_problems() -> Set[str]:
    """Scan existing .java files and tracker to find all completed problems."""
    ensure_directories()
    solved = set()
    for p in DSA_DIR.glob("*.java"):
        solved.add(p.stem.lower())
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

    return active_topic, chosen

def clean_java_code(code: str) -> str:
    """Strip any accidental markdown code fences."""
    cleaned = code.strip()
    if cleaned.startswith("```"):
        cleaned = re.sub(r"^```(?:java)?\r?\n?", "", cleaned)
        cleaned = re.sub(r"\r?\n?```$", "", cleaned)
    return cleaned.strip()

def compile_check(file_path: Path) -> bool:
    """
    Verify that the Java file compiles cleanly with javac.
    Immediately cleans up any generated .class files so only .java remains.
    """
    javac = shutil.which("javac")
    if not javac:
        print(f"[INFO] 'javac' not found. Skipping local bytecode compilation check for {file_path.name}.")
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
    Generate n strictly increasing evening timestamps between 8:30 PM (20:30)
    and 11:55 PM (23:55) IST (+05:30) for natural human pacing.
    """
    start_minutes = 20 * 60 + 30  # 20:30 IST
    end_minutes = 23 * 60 + 55    # 23:55 IST
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
# Generation Logic
# ---------------------------------------------------------------------------
def request_dsa_batch(
    target_count: int,
    active_topic: str,
    target_problems: List[str],
    all_solved: Set[str]
) -> List[JavaDSAProblem]:
    """Fetch next set of problem solutions from Gemini."""
    api_key = os.environ.get("GEMINI_API_KEY")
    if not api_key:
        print("[ERROR] GEMINI_API_KEY environment variable is not set!")
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
        curriculum_hint = f"Focus on these sequential problems for today:\n- " + "\n- ".join(target_problems)

    prompt = f"""You are Sujay, writing high-quality, hand-crafted Java DSA solutions for your personal notes and GitHub practice repository.

Topic: {active_topic}
Number of problems: Exactly {target_count}
{curriculum_hint}

Previously completed problems (do not repeat):
{json.dumps(sorted(list(all_solved))[:60])}

For each problem:
1. `class_name`: PascalCase Java class name (e.g. `TwoSum`, `KadanesAlgorithm`, `MergeTwoSortedLists`).
2. `topic`: The topic name (e.g. `{active_topic}`).
3. `problem_title`: Clear problem title.
4. `commit_message`: Clean, personal commit message like:
   - `feat(dsa): solve <ClassName> with test cases`
   - `feat(dsa): implement <ClassName> (<Topic>)`
5. `java_code`: Complete, self-contained, 100% compilable Java program:
   - Header Javadoc comment containing:
     * Problem Statement
     * Example Input & Output with brief explanation
     * Intuition & Approach
     * Time Complexity with rationale
     * Space Complexity with rationale
   - Exactly ONE `public class <ClassName>` matching `class_name`.
   - Any helper data structures (such as `ListNode`, `TreeNode`, `Pair`) MUST be declared as `static class` inside `<ClassName>` or package-private helper classes so the file compiles cleanly with `javac`.
   - Clean, natural code with helpful comments explaining the logic.
   - Fully working `public static void main(String[] args)` method with 2-3 practical test cases and formatted `System.out.println` output showing results.
   - All necessary standard library imports (e.g. `import java.util.*;`) placed at the top.
   - DO NOT include any comments or notes mentioning AI, generation, bots, or automation. The code and comments must look 100% like a dedicated student's personal study notes.

Provide exactly {target_count} sequential problems.
"""

    model_candidates = [DEFAULT_MODEL, "gemini-3.8-flash", "gemini-2.5-flash"]
    models_to_try = []
    for m in model_candidates:
        if m and m not in models_to_try:
            models_to_try.append(m)

    last_error = None
    for model_name in models_to_try:
        print(f"[INFO] Requesting {target_count} problems using model '{model_name}'...")
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

            if hasattr(response, "parsed") and response.parsed and hasattr(response.parsed, "problems"):
                batch = response.parsed
                if batch.problems:
                    print(f"[SUCCESS] Prepared {len(batch.problems)} problems.")
                    return batch.problems

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

    raise RuntimeError(f"All model attempts failed. Last error: {last_error}")

# ---------------------------------------------------------------------------
# Git Workflow
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

    print(f"\n-------------------------------------------------------")
    print(f"Problem: {class_name} ({problem.topic})")
    print(f"Time: {timestamp.strftime('%I:%M %p IST')}")
    print(f"Message: {problem.commit_message}")
    print(f"-------------------------------------------------------")

    # 1. Write Java file
    with open(java_file_path, "w", encoding="utf-8") as f:
        f.write(clean_code + "\n")
    print(f"Saved: DSAprogress/{java_filename}")

    # 2. Update tracker
    tracker_entry = {
        "class_name": class_name,
        "topic": problem.topic,
        "problem_title": problem.problem_title,
        "date_solved": timestamp.date().isoformat(),
        "commit_timestamp": timestamp.isoformat(),
        "commit_message": problem.commit_message,
        "file_path": f"DSAprogress/{java_filename}"
    }

    existing_entries = [e for e in tracker_data.get("solved_problems", []) if e.get("class_name") != class_name]
    existing_entries.append(tracker_entry)
    tracker_data["solved_problems"] = existing_entries
    tracker_data["total_solved"] = len(existing_entries)
    tracker_data["last_updated"] = timestamp.isoformat()
    save_tracker(tracker_data)

    # 3. Compile verification
    compiled = compile_check(java_file_path)
    if not compiled:
        print(f"[WARN] Compilation issue encountered for {class_name}. Storing file for review.")

    if dry_run:
        print(f"[DRY-RUN] Staging and push skipped.")
        return True

    # 4. Stage files
    run_git_cmd(["add", f"DSAprogress/{java_filename}", "DSAprogress/progress_tracker.json"])

    # 5. Commit with evening IST timestamp
    ts_str = timestamp.strftime("%Y-%m-%dT%H:%M:%S%z")
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
        print(f"Committed: {commit_res.stdout.strip()}")
    except subprocess.CalledProcessError as e:
        print(f"[ERROR] Commit failed:\n{e.stderr}")
        return False

    # 6. Push to origin main
    try:
        push_res = run_git_cmd(["push", "origin", "main"], env=git_env)
        print(f"Pushed {class_name} to main successfully.")
    except subprocess.CalledProcessError as e:
        print(f"[ERROR] Push failed:\n{e.stderr}")
        return False

    return True

# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------
def main():
    parser = argparse.ArgumentParser(description="Daily Java DSA Practice Utility")
    parser.add_argument("--dry-run", action="store_true", help="Generate code without git commit & push")
    parser.add_argument("--count", type=int, choices=[4, 5], default=None, help="Force problem count (4 or 5)")
    args = parser.parse_args()

    problem_count = args.count if args.count else random.randint(4, 5)
    print(f"Starting practice session: {problem_count} problems.")

    solved_set = scan_existing_problems()
    print(f"Previously completed: {len(solved_set)} problems.")

    active_topic, target_problems = select_next_curriculum_targets(solved_set, problem_count)
    print(f"Active Topic: {active_topic}")
    if target_problems:
        print(f"Target Problems: {', '.join(target_problems)}")

    now_ist = datetime.now(IST_TZ)
    today_ist = now_ist.date()
    timestamps = generate_staggered_timestamps(problem_count, today_ist)

    problems = request_dsa_batch(
        target_count=problem_count,
        active_topic=active_topic,
        target_problems=target_problems,
        all_solved=solved_set
    )

    tracker_data = load_tracker()

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

        if idx < len(problems) - 1 and not args.dry_run:
            pacing_delay = int(os.environ.get("PUSH_DELAY_SECONDS", random.randint(15, 25)))
            print(f"Pausing {pacing_delay}s before next problem...")
            time.sleep(pacing_delay)

    print(f"\nSession complete. {total_processed} problems saved and synced.")

if __name__ == "__main__":
    main()
