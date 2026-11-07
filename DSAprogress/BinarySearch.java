import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums. If target exists, then return its index.
 * Otherwise, return -1.
 *
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4.
 *
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1.
 *
 * Intuition & Approach:
 * Binary Search is a divide-and-conquer algorithm designed for sorted collections.
 * We maintain two boundaries: 'low' starting at index 0 and 'high' at (nums.length - 1).
 * In each iteration:
 * - Calculate the middle index: mid = low + (high - low) / 2 to avoid potential integer overflow.
 * - If nums[mid] == target, we found the target and return mid.
 * - If nums[mid] < target, the target must reside in the right half, so low = mid + 1.
 * - If nums[mid] > target, the target must reside in the left half, so high = mid - 1.
 * If low exceeds high, the element does not exist in the array, so we return -1.
 *
 * Time Complexity:
 * O(log n) - The search space is halved in each step.
 *
 * Space Complexity:
 * O(1) - Iterative approach requires constant extra space.
 */
public class BinarySearch {

    /**
     * Searches for target in a sorted integer array using iterative binary search.
     *
     * @param nums sorted array of integers
     * @param target value to search for
     * @return index of target if found, otherwise -1
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents overflow compared to (low + high) / 2

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        int target2 = 2;

        int[] nums2 = {5};
        int target3 = 5;
        int target4 = -5;

        System.out.println("=== Binary Search ===");
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Search for " + target1 + " -> Index: " + search(nums1, target1));
        System.out.println("Search for " + target2 + " -> Index: " + search(nums1, target2));
        System.out.println();

        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Search for " + target3 + " -> Index: " + search(nums2, target3));
        System.out.println("Search for " + target4 + " -> Index: " + search(nums2, target4));
    }
}
