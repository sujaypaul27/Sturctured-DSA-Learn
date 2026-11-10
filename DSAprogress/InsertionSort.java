import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of integers, sort the array in ascending order using the Insertion Sort algorithm.
 *
 * Examples:
 * Input: nums = [12, 11, 13, 5, 6]
 * Output: [5, 6, 11, 12, 13]
 * Explanation: Elements from index 1 to n - 1 are picked one by one and inserted into their
 * correct relative position in the sorted prefix.
 *
 * Input: nums = [4, 3, 2, 10, 12, 1, 5, 6]
 * Output: [1, 2, 3, 4, 5, 6, 10, 12]
 *
 * Intuition & Approach:
 * - Insertion sort works analogously to how people sort playing cards in their hands.
 * - We start with a sorted subarray of length 1 (nums[0]).
 * - For every next element `key = nums[i]`:
 *   - Compare `key` with elements to its left in reverse order.
 *   - Shift all elements that are greater than `key` one position to the right.
 *   - Place `key` in its correct empty slot.
 * - Stable sorting algorithm that performs exceptionally well on small or nearly sorted arrays.
 *
 * Complexity Analysis:
 * - Time Complexity:
 *   - Worst & Average Case: O(N^2), when array is sorted in reverse order.
 *   - Best Case: O(N), when the array is already sorted (inner while loop breaks immediately).
 * - Space Complexity: O(1), in-place sorting.
 */
public class InsertionSort {

    /**
     * Sorts the given array in ascending order using Insertion Sort.
     *
     * @param nums array to sort
     */
    public static void insertionSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int key = nums[i];
            int j = i - 1;

            // Move elements of nums[0..i-1] that are greater than key one position ahead
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }

            // Place the key at its correct position
            nums[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {12, 11, 13, 5, 6};
        System.out.println("Before Sorting: " + Arrays.toString(arr1));
        insertionSort(arr1);
        System.out.println("After Insertion Sort: " + Arrays.toString(arr1));
        System.out.println();

        int[] arr2 = {4, 3, 2, 10, 12, 1, 5, 6};
        System.out.println("Before Sorting: " + Arrays.toString(arr2));
        insertionSort(arr2);
        System.out.println("After Insertion Sort: " + Arrays.toString(arr2));
        System.out.println();

        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Before Sorting (Already Sorted): " + Arrays.toString(arr3));
        insertionSort(arr3);
        System.out.println("After Insertion Sort:              " + Arrays.toString(arr3));
    }
}
