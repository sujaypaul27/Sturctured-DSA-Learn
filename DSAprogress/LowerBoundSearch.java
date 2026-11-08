import java.util.Arrays;

/**
 * Problem Statement:
 * Given a sorted array of integers `nums` and a target value `target`,
 * find the lower bound index of `target`.
 * The lower bound is the smallest index `i` such that `nums[i] >= target`.
 * If no such element exists, return the length of the array `nums.length`.
 *
 * Example 1:
 * Input: nums = [1, 2, 4, 4, 5, 6, 8, 10], target = 4
 * Output: 2
 * Explanation: nums[2] is 4, which is >= 4, and it is the earliest occurrence.
 *
 * Example 2:
 * Input: nums = [1, 2, 4, 4, 5, 6, 8, 10], target = 7
 * Output: 6
 * Explanation: nums[6] is 8, which is the smallest element >= 7.
 *
 * Intuition & Approach:
 * Since the array is sorted, we can use binary search.
 * Maintain a candidate answer initialized to `nums.length`.
 * At each step, compute `mid`:
 * - If nums[mid] >= target, it could be our answer, but there could be an
 *   even smaller valid index to the left. Record mid in `ans` and search left (`high = mid - 1`).
 * - If nums[mid] < target, the answer must lie strictly to the right (`low = mid + 1`).
 *
 * Time Complexity:
 * O(log N) - The search space is halved at each step of binary search.
 *
 * Space Complexity:
 * O(1) - Only a few pointer variables are used.
 */
public class LowerBoundSearch {

    /**
     * Finds the lowest index i such that nums[i] >= target.
     * 
     * @param nums sorted array of integers
     * @param target search target
     * @return smallest index satisfying nums[index] >= target, or nums.length
     */
    public static int findLowerBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length; // Default if target is strictly greater than all elements

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                ans = mid;         // Potential lower bound found
                high = mid - 1;    // Look for a smaller index on the left
            } else {
                low = mid + 1;     // Must look on the right side
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 4, 5, 6, 8, 10};
        int target1 = 4;
        System.out.println("Array: " + Arrays.toString(arr1));
        System.out.println("Lower bound for " + target1 + ": " + findLowerBound(arr1, target1));

        int target2 = 7;
        System.out.println("Lower bound for " + target2 + ": " + findLowerBound(arr1, target2));

        int target3 = 11;
        System.out.println("Lower bound for " + target3 + ": " + findLowerBound(arr1, target3));
    }
}
