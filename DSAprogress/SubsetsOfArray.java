import java.util.*;

/**
 * Problem Statement:
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 * Input: nums = [1, 2, 3]
 * Output: [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [[], [0]]
 *
 * Intuition & Approach:
 * We use a recursive backtracking approach:
 * At each index `i`, we have two choices for `nums[i]`:
 * 1. Include `nums[i]` in current subset.
 * 2. Exclude `nums[i]` from current subset.
 * When index reaches nums.length, we add a copy of the current subset to results.
 * Alternatively, we can use the loop-based recursion pattern to pick the next element.
 *
 * Time Complexity:
 * O(n * 2^n) - There are 2^n subsets, and copying each subset to the answer list takes O(n).
 *
 * Space Complexity:
 * O(n) auxiliary recursion stack space, excluding output storage.
 */
public class SubsetsOfArray {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generateSubsets(nums, 0, current, result);
        return result;
    }

    private static void generateSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Add a copy of current subset at every step
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            // Pick element
            current.add(nums[i]);
            // Recurse for remaining elements
            generateSubsets(nums, i + 1, current, result);
            // Backtrack: undo choice
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Array of 3 elements
        int[] nums1 = {1, 2, 3};
        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Subsets: " + subsets(nums1));
        System.out.println();

        // Test Case 2: Array of 1 element
        int[] nums2 = {5};
        System.out.println("Test Case 2:");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Subsets: " + subsets(nums2));
        System.out.println();

        // Test Case 3: Empty array
        int[] nums3 = {};
        System.out.println("Test Case 3:");
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Subsets: " + subsets(nums3));
    }
}
