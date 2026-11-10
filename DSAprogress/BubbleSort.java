import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of integers, sort the array in ascending order using the Bubble Sort algorithm.
 * Optimize the algorithm so that it terminates early if the array is already sorted.
 *
 * Examples:
 * Input: nums = [64, 34, 25, 12, 22, 11, 90]
 * Output: [11, 12, 22, 25, 34, 64, 90]
 * Explanation: After successive pairwise comparisons and swaps, largest elements bubble up to the end.
 *
 * Input: nums = [1, 2, 3, 4, 5]
 * Output: [1, 2, 3, 4, 5]
 * Explanation: Already sorted; early termination triggers after 1 pass.
 *
 * Intuition & Approach:
 * - Repeatedly step through the list, compare adjacent elements, and swap them if they are
 *   in the wrong order (nums[j] > nums[j + 1]).
 * - After pass `i`, the largest element among the remaining unsorted prefix settles at index `n - 1 - i`.
 * - Optimization: If in any pass no swaps occur, the array is already sorted, so we can break early.
 *
 * Complexity Analysis:
 * - Time Complexity:
 *   - Worst & Average Case: O(N^2), when elements are in reverse or random order.
 *   - Best Case: O(N), when the array is already sorted.
 * - Space Complexity: O(1), in-place sorting algorithm.
 */
public class BubbleSort {

    /**
     * Sorts the given array in ascending order using optimized Bubble Sort.
     *
     * @param nums array to sort
     */
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            // Last i elements are already in their correct sorted positions
            for (int j = 0; j < n - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no elements were swapped in this pass, array is sorted
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before Sorting: " + Arrays.toString(arr1));
        bubbleSort(arr1);
        System.out.println("After Bubble Sort:  " + Arrays.toString(arr1));
        System.out.println();

        int[] arr2 = {5, 1, 4, 2, 8};
        System.out.println("Before Sorting: " + Arrays.toString(arr2));
        bubbleSort(arr2);
        System.out.println("After Bubble Sort:  " + Arrays.toString(arr2));
        System.out.println();

        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Before Sorting (Already Sorted): " + Arrays.toString(arr3));
        bubbleSort(arr3);
        System.out.println("After Bubble Sort:              " + Arrays.toString(arr3));
    }
}
