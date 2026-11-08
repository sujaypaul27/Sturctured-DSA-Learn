import java.util.Arrays;

/**
 * Problem Statement:
 * Given a sorted array of integers `nums` and a value `x`, find the floor and ceiling of `x`.
 * - Floor of `x`: The largest element in the array smaller than or equal to `x`.
 *                 If no such element exists, return -1.
 * - Ceil of `x`:  The smallest element in the array greater than or equal to `x`.
 *                 If no such element exists, return -1.
 *
 * Example 1:
 * Input: nums = [3, 4, 4, 7, 8, 10], x = 5
 * Output: Floor = 4, Ceil = 7
 * Explanation: Largest element <= 5 is 4; smallest element >= 5 is 7.
 *
 * Example 2:
 * Input: nums = [3, 4, 4, 7, 8, 10], x = 2
 * Output: Floor = -1, Ceil = 3
 * Explanation: No element <= 2 exists, so floor is -1. Smallest >= 2 is 3.
 *
 * Intuition & Approach:
 * 1. Finding Floor:
 *    - If nums[mid] <= x, nums[mid] is a candidate floor. Store it and explore the right half
 *      (low = mid + 1) to see if a larger valid element exists.
 *    - If nums[mid] > x, search the left half (high = mid - 1).
 * 2. Finding Ceil:
 *    - Ceil is equivalent to the Lower Bound value.
 *    - If nums[mid] >= x, nums[mid] is a candidate ceil. Store it and explore the left half
 *      (high = mid - 1) to see if a smaller valid element exists.
 *    - If nums[mid] < x, search the right half (low = mid + 1).
 *
 * Time Complexity:
 * O(log N) - Both floor and ceil are computed via binary search.
 *
 * Space Complexity:
 * O(1) - Constant auxiliary space.
 */
public class FloorAndCeilInSortedArray {

    public static class Result {
        public final int floor;
        public final int ceil;

        public Result(int floor, int ceil) {
            this.floor = floor;
            this.ceil = ceil;
        }

        @Override
        public String toString() {
            return "Floor: " + floor + ", Ceil: " + ceil;
        }
    }

    /**
     * Finds the largest element <= x.
     */
    public static int findFloor(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        int floor = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= x) {
                floor = nums[mid]; // Valid floor candidate
                low = mid + 1;     // Look for a larger element <= x on the right
            } else {
                high = mid - 1;
            }
        }

        return floor;
    }

    /**
     * Finds the smallest element >= x.
     */
    public static int findCeil(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        int ceil = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= x) {
                ceil = nums[mid];  // Valid ceil candidate
                high = mid - 1;    // Look for a smaller element >= x on the left
            } else {
                low = mid + 1;
            }
        }

        return ceil;
    }

    /**
     * Combines both floor and ceil searches.
     */
    public static Result getFloorAndCeil(int[] nums, int x) {
        int floor = findFloor(nums, x);
        int ceil = findCeil(nums, x);
        return new Result(floor, ceil);
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 4, 7, 8, 10};
        System.out.println("Sorted Array: " + Arrays.toString(nums));

        int x1 = 5;
        System.out.println("Query x = " + x1 + " -> " + getFloorAndCeil(nums, x1));

        int x2 = 2;
        System.out.println("Query x = " + x2 + " -> " + getFloorAndCeil(nums, x2));

        int x3 = 10;
        System.out.println("Query x = " + x3 + " -> " + getFloorAndCeil(nums, x3));

        int x4 = 11;
        System.out.println("Query x = " + x4 + " -> " + getFloorAndCeil(nums, x4));
    }
}
