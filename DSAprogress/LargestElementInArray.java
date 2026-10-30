import java.util.*;

/**
 * Problem Statement:
 * Given an array of integers, find the largest element in the array.
 * Implement both recursive and iterative approaches to demonstrate recursion fundamentals.
 *
 * Example 1:
 * Input: nums = [2, 5, 1, 3, 0]
 * Output: 5
 *
 * Example 2:
 * Input: nums = [8, 10, 5, 7, 9]
 * Output: 10
 *
 * Intuition & Approach:
 * 1. Recursive: Largest in array of size n is max(nums[n - 1], largest in array of size n - 1).
 *    Base case: if n == 1, the only element is the largest.
 * 2. Iterative: Maintain a `max` variable, traverse the array, and update whenever a bigger number is found.
 *
 * Time Complexity:
 * - Recursive: O(n) - Visits each element once.
 * - Iterative: O(n) - Single pass through the array.
 *
 * Space Complexity:
 * - Recursive: O(n) auxiliary call stack space.
 * - Iterative: O(1) constant auxiliary space.
 */
public class LargestElementInArray {

    // Recursive solution
    public static int findMaxRecursive(int[] nums, int n) {
        // Base case: only one element left
        if (n == 1) {
            return nums[0];
        }
        // Return maximum of current element and max of rest of the array
        return Math.max(nums[n - 1], findMaxRecursive(nums, n - 1));
    }

    // Iterative solution
    public static int findMaxIterative(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be empty.");
        }
        int maxVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }
        return maxVal;
    }

    public static void main(String[] args) {
        // Test Case 1: Standard unsorted array
        int[] arr1 = {2, 5, 1, 3, 0};
        System.out.println("Test Case 1:");
        System.out.println("Array: " + Arrays.toString(arr1));
        System.out.println("Max (Recursive): " + findMaxRecursive(arr1, arr1.length));
        System.out.println("Max (Iterative): " + findMaxIterative(arr1));
        System.out.println();

        // Test Case 2: Array with negative numbers
        int[] arr2 = {-10, -3, -25, -1, -8};
        System.out.println("Test Case 2:");
        System.out.println("Array: " + Arrays.toString(arr2));
        System.out.println("Max (Recursive): " + findMaxRecursive(arr2, arr2.length));
        System.out.println("Max (Iterative): " + findMaxIterative(arr2));
        System.out.println();

        // Test Case 3: Single element array
        int[] arr3 = {42};
        System.out.println("Test Case 3:");
        System.out.println("Array: " + Arrays.toString(arr3));
        System.out.println("Max (Recursive): " + findMaxRecursive(arr3, arr3.length));
        System.out.println("Max (Iterative): " + findMaxIterative(arr3));
    }
}
