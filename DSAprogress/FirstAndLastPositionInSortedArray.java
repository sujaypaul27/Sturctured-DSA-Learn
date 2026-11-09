package searching_and_sorting;

import java.util.Arrays;

/**
 * Problem: Find First and Last Position of Element in Sorted Array
 *
 * Problem Statement:
 * Given an array of integers nums sorted in non-decreasing order, find the starting
 * and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [5, 7, 7, 8, 8, 10], target = 8
 * Output: [3, 4]
 *
 * Example 2:
 * Input: nums = [5, 7, 7, 8, 8, 10], target = 6
 * Output: [-1, -1]
 *
 * Intuition & Approach:
 * A linear scan takes O(n) time, but because the array is sorted, we can use binary search.
 * We perform two separate binary searches:
 * 1. To find the first occurrence: When nums[mid] == target, this could be our answer,
 *    but there might be another occurrence to the left, so we record mid and shrink the search
 *    space to the left (high = mid - 1).
 * 2. To find the last occurrence: When nums[mid] == target, we record mid and shrink
 *    the search space to the right (low = mid + 1).
 *
 * Time Complexity:
 * - O(log n) because we execute two binary searches, each halving the search space.
 *
 * Space Complexity:
 * - O(1) auxiliary space as we only use a few pointer variables.
 */
public class FirstAndLastPositionInSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int low = 0;
        int high = nums.length - 1;
        int bound = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    // Look towards the left for earlier occurrence
                    high = mid - 1;
                } else {
                    // Look towards the right for later occurrence
                    low = mid + 1;
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return bound;
    }

    public static void main(String[] args) {
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(searchRange(nums1, target1)));

        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(searchRange(nums2, target2)));

        int[] nums3 = {};
        int target3 = 0;
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(searchRange(nums3, target3)));
    }
}
