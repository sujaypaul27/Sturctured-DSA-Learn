import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of integers, sort the array in ascending order using the Selection Sort algorithm.
 *
 * Examples:
 * Input: nums = [64, 25, 12, 22, 11]
 * Output: [11, 12, 22, 25, 64]
 * Explanation: At each pass, the minimum element from the unsorted sub-array is selected
 * and swapped with the element at the beginning of the unsorted sub-array.
 *
 * Input: nums = [5, 4, 3, 2, 1]
 * Output: [1, 2, 3, 4, 5]
 *
 * Intuition & Approach:
 * - Maintain two subarrays in the given array: sorted portion and unsorted portion.
 * - Initially, sorted portion is empty and unsorted portion is the whole array.
 * - In each iteration `i` from 0 to n - 2:
 *   - Find the index of the minimum element in nums[i ... n - 1].
 *   - Swap that minimum element with nums[i].
 * - Guarantees that at most O(N) swaps are performed, which is useful when write operations are costly.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N^2) for all cases (best, average, worst) because finding the minimum
 *   requires scanning the remaining elements regardless of initial ordering.
 * - Space Complexity: O(1), performs in-place sorting.
 */
public class SelectionSort {

    /**
     * Sorts the given array in ascending order using Selection Sort.
     *
     * @param nums array to sort
     */
    public static void selectionSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Search for the minimum element in the remaining unsorted portion
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap minimum found with the first element of unsorted portion
            if (minIndex != i) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {64, 25, 12, 22, 11};
        System.out.println("Before Sorting: " + Arrays.toString(arr1));
        selectionSort(arr1);
        System.out.println("After Selection Sort: " + Arrays.toString(arr1));
        System.out.println();

        int[] arr2 = {29, 10, 14, 37, 13};
        System.out.println("Before Sorting: " + Arrays.toString(arr2));
        selectionSort(arr2);
        System.out.println("After Selection Sort: " + Arrays.toString(arr2));
        System.out.println();

        int[] arr3 = {3, 1, 2};
        System.out.println("Before Sorting: " + Arrays.toString(arr3));
        selectionSort(arr3);
        System.out.println("After Selection Sort: " + Arrays.toString(arr3));
    }
}
