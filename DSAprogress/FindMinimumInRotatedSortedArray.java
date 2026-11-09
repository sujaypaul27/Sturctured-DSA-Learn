package searching_and_sorting;

import java.util.Arrays;

/**
 * Problem: Find Minimum in Rotated Sorted Array
 *
 * Problem Statement:
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [3, 4, 5, 1, 2]
 * Output: 1 (Original array was [1, 2, 3, 4, 5] rotated 3 times)
 *
 * Example 2:
 * Input: nums = [4, 5, 6, 7, 0, 1, 2]
 * Output: 0
 *
 * Example 3:
 * Input: nums = [11, 13, 15, 17]
 * Output: 11
 *
 * Intuition & Approach:
 * The minimum element is the pivot point where the order breaks, or nums[0] if the array
 * is completely unrotated.
 * We can compare nums[mid] with nums[high]:
 * - If nums[mid] > nums[high], the pivot and the minimum element must lie in the right half
 *   (low = mid + 1).
 * - If nums[mid] <= nums[high], the right half is sorted, and the minimum element could be
 *   nums[mid] or lies to its left (high = mid).
 * When low == high, both pointers converge to the minimum element.
 *
 * Time Complexity:
 * - O(log n) because the search space is divided in half at each iteration.
 *
 * Space Complexity:
 * - O(1) auxiliary space as only constant extra variables are used.
 */
public class FindMinimumInRotatedSortedArray {

    public static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                // The minimum is strictly in the right half
                low = mid + 1;
            } else {
                // The minimum is mid or lies in the left half
                high = mid;
            }
        }

        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Minimum: " + findMin(nums1));

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Minimum: " + findMin(nums2));

        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Minimum: " + findMin(nums3));
    }
}
