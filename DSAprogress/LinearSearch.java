/**
 * Problem Statement:
 * Given an array of integers nums and an integer target, write a function to search for target in nums.
 * If target exists, return its first index. Otherwise, return -1.
 *
 * Example 1:
 * Input: nums = [4, 2, 7, 1, 9, 3], target = 7
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1, 5, 8, 2], target = 10
 * Output: -1
 *
 * Intuition & Approach:
 * Linear search sequentially checks each element of the array from index 0 to n - 1
 * until a match is found or the end of the array is reached.
 * It is the most fundamental search algorithm and does not require the array to be sorted.
 *
 * Time Complexity:
 * O(n) - In the worst case (element not present or at the end), we examine all n elements.
 * Best case is O(1) if target is at index 0.
 *
 * Space Complexity:
 * O(1) - No auxiliary memory required beyond index counter.
 */
public class LinearSearch {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // Test Case 1: Target present in middle
        int[] arr1 = {4, 2, 7, 1, 9, 3};
        int target1 = 7;
        int result1 = search(arr1, target1);
        System.out.println("Test Case 1: Target " + target1 + " found at index: " + result1);

        // Test Case 2: Target not present
        int[] arr2 = {10, 20, 30, 40};
        int target2 = 25;
        int result2 = search(arr2, target2);
        System.out.println("Test Case 2: Target " + target2 + " found at index: " + result2);

        // Test Case 3: Target is first element
        int[] arr3 = {5, 12, 18, 9};
        int target3 = 5;
        int result3 = search(arr3, target3);
        System.out.println("Test Case 3: Target " + target3 + " found at index: " + result3);
    }
}
