package searching_and_sorting;

import java.util.Arrays;

/**
 * Problem: Count Occurrences of a Target in Sorted Array
 *
 * Problem Statement:
 * Given a sorted array of n integers and a target value x, count the number of occurrences of x
 * in the array in O(log n) time.
 *
 * Example 1:
 * Input: nums = [1, 1, 2, 2, 2, 2, 3], target = 2
 * Output: 4 (2 appears 4 times)
 *
 * Example 2:
 * Input: nums = [1, 1, 2, 3], target = 4
 * Output: 0 (4 does not appear in the array)
 *
 * Intuition & Approach:
 * Since the array is sorted, all identical elements are contiguous.
 * Instead of traversing linearly (which takes O(n)), we can find the index of the first
 * occurrence and the index of the last occurrence of the target using binary search.
 * - If target does not exist, first occurrence returns -1, and count is 0.
 * - Otherwise, total count = (lastIndex - firstIndex + 1).
 *
 * Time Complexity:
 * - O(log n) because we perform two binary search passes.
 *
 * Space Complexity:
 * - O(1) auxiliary space as we only maintain pointers.
 */
public class CountOccurrencesInSortedArray {

    public static int count(int[] nums, int target) {
        int first = findFirstOccurrence(nums, target);
        if (first == -1) {
            return 0;
        }
        int last = findLastOccurrence(nums, target);
        return last - first + 1;
    }

    private static int findFirstOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int first = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                first = mid;
                high = mid - 1; // keep searching left
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return first;
    }

    private static int findLastOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int last = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                last = mid;
                low = mid + 1; // keep searching right
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2, 2, 2, 2, 3};
        int target1 = 2;
        System.out.println("Array: " + Arrays.toString(nums1) + ", Target: " + target1);
        System.out.println("Count: " + count(nums1, target1));

        int[] nums2 = {1, 1, 2, 3};
        int target2 = 4;
        System.out.println("Array: " + Arrays.toString(nums2) + ", Target: " + target2);
        System.out.println("Count: " + count(nums2, target2));

        int[] nums3 = {5, 5, 5, 5, 5};
        int target3 = 5;
        System.out.println("Array: " + Arrays.toString(nums3) + ", Target: " + target3);
        System.out.println("Count: " + count(nums3, target3));
    }
}
