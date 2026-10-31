import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of integers nums, return true if the array was originally sorted in
 * non-decreasing order, then rotated some number of positions (including zero).
 * Otherwise, return false.
 *
 * Example 1:
 * Input: nums = [3, 4, 5, 1, 2]
 * Output: true
 * Explanation: [1, 2, 3, 4, 5] is the original sorted array, rotated by 3 positions.
 *
 * Example 2:
 * Input: nums = [2, 1, 3, 4]
 * Output: false
 * Explanation: There is no sorted array that can be rotated to yield this array.
 *
 * Example 3:
 * Input: nums = [1, 2, 3]
 * Output: true
 * Explanation: Rotated by 0 positions (already sorted).
 *
 * Intuition & Approach:
 * A non-decreasing sorted array that is rotated at most once can have at most one inversion
 * (drop point) where nums[i] > nums[(i + 1) % n].
 * - If the count of such drops is 0: the array is sorted and all elements are in order.
 * - If the count of such drops is 1: it corresponds to the single pivot point where rotation happened,
 *   and the wrap-around check nums[n - 1] <= nums[0] ensures circular sorted order.
 * - If the count of drops is greater than 1: it cannot be formed by rotating a sorted array.
 *
 * Time Complexity:
 * O(N) where N is the length of the array, since we check each adjacent circular pair once.
 *
 * Space Complexity:
 * O(1) auxiliary space as we only use a counter.
 */
public class CheckIfArraySorted {

    public static boolean check(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }

        int countDrops = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // Compare current element with next element in a circular manner
            if (nums[i] > nums[(i + 1) % n]) {
                countDrops++;
            }
            if (countDrops > 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] test1 = {3, 4, 5, 1, 2};
        System.out.println("Array: " + Arrays.toString(test1) + " -> Is Sorted & Rotated: " + check(test1));

        int[] test2 = {2, 1, 3, 4};
        System.out.println("Array: " + Arrays.toString(test2) + " -> Is Sorted & Rotated: " + check(test2));

        int[] test3 = {1, 2, 3, 4, 5};
        System.out.println("Array: " + Arrays.toString(test3) + " -> Is Sorted & Rotated: " + check(test3));

        int[] test4 = {1, 1, 1};
        System.out.println("Array: " + Arrays.toString(test4) + " -> Is Sorted & Rotated: " + check(test4));
    }
}
