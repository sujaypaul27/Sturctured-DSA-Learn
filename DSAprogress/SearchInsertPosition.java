import java.util.Arrays;

/**
 * Problem Statement:
 * Given a sorted array of distinct integers and a target value,
 * return the index if the target is found. If not, return the index
 * where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [1, 3, 5, 6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1, 3, 5, 6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1, 3, 5, 6], target = 7
 * Output: 4
 *
 * Intuition & Approach:
 * The problem is equivalent to finding the Lower Bound of `target`:
 * the smallest index `i` where `nums[i] >= target`.
 * - If target is present, `nums[i] == target`, returning its index.
 * - If target is absent, `nums[i]` will be the first element greater than target,
 *   which is precisely where `target` should be inserted to maintain sorted order.
 * - If target is greater than all elements, the answer defaults to `nums.length`.
 *
 * Time Complexity:
 * O(log N) - Standard binary search halving the interval each iteration.
 *
 * Space Complexity:
 * O(1) - In-place binary search requiring no auxiliary space.
 */
public class SearchInsertPosition {

    /**
     * Returns the index of target in nums, or the index where it should be inserted.
     * 
     * @param nums sorted array of distinct integers
     * @param target value to search or insert
     * @return index position
     */
    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                ans = mid;         // Can insert at or before mid
                high = mid - 1;
            } else {
                low = mid + 1;     // Target is larger, must be inserted after mid
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};

        int target1 = 5;
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("SearchInsert for " + target1 + ": " + searchInsert(nums, target1)); // 2

        int target2 = 2;
        System.out.println("SearchInsert for " + target2 + ": " + searchInsert(nums, target2)); // 1

        int target3 = 7;
        System.out.println("SearchInsert for " + target3 + ": " + searchInsert(nums, target3)); // 4

        int target4 = 0;
        System.out.println("SearchInsert for " + target4 + ": " + searchInsert(nums, target4)); // 0
    }
}
