import java.util.Arrays;

/**
 * Problem Statement:
 * Given a sorted array of integers `nums` and a target value `target`,
 * find the upper bound index of `target`.
 * The upper bound is the smallest index `i` such that `nums[i] > target`.
 * If no such element exists, return the length of the array `nums.length`.
 *
 * Example 1:
 * Input: nums = [2, 3, 5, 5, 5, 8, 12], target = 5
 * Output: 5
 * Explanation: nums[5] = 8, which is the first element strictly greater than 5.
 *
 * Example 2:
 * Input: nums = [2, 3, 5, 5, 5, 8, 12], target = 12
 * Output: 7
 * Explanation: No element is strictly greater than 12, so returns length (7).
 *
 * Intuition & Approach:
 * Similar to lower bound, but the condition shifts from `>=` to `>`.
 * - If `nums[mid] > target`, it could be our answer. Store `mid` in `ans` and search left (`high = mid - 1`).
 * - If `nums[mid] <= target`, `mid` and all elements to its left cannot be strictly greater than target,
 *   so search right (`low = mid + 1`).
 *
 * Time Complexity:
 * O(log N) - Logarithmic search time over sorted array of size N.
 *
 * Space Complexity:
 * O(1) - Constant extra space used.
 */
public class UpperBoundSearch {

    /**
     * Finds the lowest index i such that nums[i] > target.
     * 
     * @param nums sorted array of integers
     * @param target search target
     * @return smallest index satisfying nums[index] > target, or nums.length
     */
    public static int findUpperBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length; // Default if target is >= all elements

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                ans = mid;         // Candidate upper bound found
                high = mid - 1;    // Check if an earlier index also satisfies > target
            } else {
                low = mid + 1;     // nums[mid] is <= target, move right
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 5, 5, 5, 8, 12};
        int target1 = 5;
        System.out.println("Array: " + Arrays.toString(arr1));
        System.out.println("Upper bound for " + target1 + ": " + findUpperBound(arr1, target1));

        int target2 = 1;
        System.out.println("Upper bound for " + target2 + ": " + findUpperBound(arr1, target2));

        int target3 = 12;
        System.out.println("Upper bound for " + target3 + ": " + findUpperBound(arr1, target3));
    }
}
