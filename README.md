# ☕ Data Structures & Algorithms in Java

My personal repository of Java implementations, problem-solving notes, and complexity analyses. This repository follows a structured topic-by-topic progression from core fundamentals to advanced data structures and algorithms.

---

## 📌 Repository Overview

Each solution is written as a self-contained, compilable Java class with:
- **Problem Statement & Constraints:** Summary of what the problem requires.
- **Example Walkthroughs:** Clear input, expected output, and step-by-step trace.
- **Approach & Intuition:** The core thought process, data structures chosen, and reasoning.
- **Complexity Analysis:** Theoretical time and space complexity with Big-O justifications.
- **Working Test Cases:** A `main` method executing realistic test cases with formatted console outputs.

All source files are organized inside the [`DSAprogress/`](./DSAprogress/) folder, tracked alongside a progress log in [`DSAprogress/progress_tracker.json`](./DSAprogress/progress_tracker.json).

---

## 🗺️ Learning Roadmap & Curriculum

```text
1. Basic Math & Number Logic
   └── Number properties, digit extraction, GCD/LCM, prime algorithms, modular arithmetic.

2. Patterns & Recursion Basics
   └── Base cases, recursion tree visualization, functional vs parameterized recursion, backtracking.

3. Arrays (Easy to Medium)
   └── Two pointers, sliding window, prefix sums, Kadane's algorithm, Dutch National Flag, matrix traversals.

4. Strings
   └── Anagram verification, substring matching, palindrome logic, frequency counting, string compression.

5. Searching & Sorting
   └── Binary search on arrays & search space, lower/upper bounds, merge sort, quicksort.

6. Linked Lists
   └── Singly and doubly linked lists, pointer reversal, Floyd's cycle detection, list merging.

7. Stacks & Queues
   └── Monotonic stacks, next greater element, parentheses matching, queue simulation with stacks.

8. Binary Trees & Binary Search Trees (BST)
   └── Pre/In/Post/Level order traversals, tree height/diameter, balanced trees, BST validation & operations.

9. Heaps, Graphs & Dynamic Programming
   └── Priority queues, BFS/DFS traversals, Dijkstra's algorithm, topological sorting, 1D & 2D DP.
```

---

## 📂 Project Structure

```text
Sturctured-DSA-Learn/
├── DSAprogress/
│   ├── progress_tracker.json    # Summary log of completed problems and topics
│   ├── SumOfNumbers.java        # Individual problem solutions
│   ├── TwoSum.java
│   └── ...
├── scripts/
│   └── daily_dsa_commit.py      # Study session helper script
├── requirements.txt             # Utility dependencies
├── .gitignore                   # Ignore bytecode (*.class) and temporary files
└── README.md                    # Study notes and curriculum guide
```

---

## 📝 Solution Template

Every file in `DSAprogress/` adheres to a clean, educational standard:

```java
/**
 * Problem: Two Sum
 * Topic: Arrays - Easy to Medium
 * 
 * Problem Statement:
 * Given an array of integers nums and an integer target, return indices of the two
 * numbers such that they add up to target.
 * 
 * Approach (Single-pass Hash Map):
 * - Traverse the array while maintaining a map of seen values to indices.
 * - For each element, check if (target - num) is already in the map.
 * - If found, return the pair of indices; otherwise, insert the current number and index.
 * 
 * Time Complexity: O(N) where N is array length.
 * Space Complexity: O(N) to store elements in the hash map.
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("Result: " + Arrays.toString(result)); // [0, 1]
    }
}
```

---

## 💻 Running Locally

### Compile any Java file:
```bash
javac DSAprogress/TwoSum.java
```

### Run the solution:
```bash
java -cp DSAprogress TwoSum
```

---

## 👤 Author

- **Sujay Paul**
- GitHub: [@sujaypaul27](https://github.com/sujaypaul27)
- Email: `sujaypaul2711@gmail.com`