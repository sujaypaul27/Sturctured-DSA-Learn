package searching_and_sorting;

import java.util.Arrays;

/**
 * Problem: Search in Rotated Sorted Array
 *
 * Problem Statement:
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot
 * index k (1 <= k < nums.length). Given the array nums after the possible rotation and
 * an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 3
 * Output: -1
 *
 * Intuition & Approach:
 * In a rotated sorted array, dividing the array into two halves at `mid` always leaves
 * at least one half sorted.
 * 1. Identify which half is sorted:
 *    - If nums[low] <= nums[mid], the left half is sorted.
 *    - Otherwise, the right half is sorted.
 * 2. Once the sorted half is identified, check if target falls within its range:
 *    - If it does, eliminate the other half.
 *    - If it doesn't, search in the unsorted half.
 *
 * Time Complexity:
 * - O(log n) because at each step we reduce the search space by half.
 *
 * Space Complexity:
 * - O(1) auxiliary space as only constant extra variables are used.
 */
public class SearchInRotatedSortedArray {

    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Determine which half is sorted
            if (nums[low] <= nums[mid]) {
                // Left half is sorted
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1; // target is in the left sorted range
                } else {
                    low = mid + 1;  // target must be in the right range
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;  // target is in the right sorted range
                } else {
                    high = mid - 1; // target must be in the left range
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Index: " + search(nums1, target1));

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Index: " + search(nums2, target2));

        int[] nums3 = {1};
        int target3 = 1;
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Index: " + search(nums3, target3));
    }
}
