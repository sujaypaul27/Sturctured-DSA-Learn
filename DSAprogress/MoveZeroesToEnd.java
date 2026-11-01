import java.util.Arrays;

/**
 * Problem Statement:
 * Given an integer array nums, move all 0's to the end of it while maintaining the 
 * relative order of the non-zero elements. You must do this in-place without making a copy of the array.
 *
 * Example 1:
 * Input: nums = [0, 1, 0, 3, 12]
 * Output: [1, 3, 12, 0, 0]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 *
 * Intuition & Approach:
 * Use a two-pointer technique:
 * - Pointer `insertPos` tracks the index where the next non-zero element should be placed.
 * - Iterate through the array with pointer `i`. Whenever `nums[i] != 0`:
 *   Swap `nums[insertPos]` and `nums[i]`, then increment `insertPos`.
 * - By swapping instead of overwriting, zeroes naturally shift towards the end without 
 *   requiring a second pass to fill remaining positions with zeroes.
 *
 * Time Complexity:
 * O(n) - Single traversal of the array of size n.
 *
 * Space Complexity:
 * O(1) - Constant extra space used (in-place modification).
 */
public class MoveZeroesToEnd {

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != insertPos) {
                    int temp = nums[insertPos];
                    nums[insertPos] = nums[i];
                    nums[i] = temp;
                }
                insertPos++;
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] arr1 = {0, 1, 0, 3, 12};
        System.out.println("Test Case 1:");
        System.out.println("Before: " + Arrays.toString(arr1));
        moveZeroes(arr1);
        System.out.println("After:  " + Arrays.toString(arr1));

        // Test Case 2: Array with already trailing zeroes
        int[] arr2 = {1, 2, 3, 0, 0};
        System.out.println("\nTest Case 2:");
        System.out.println("Before: " + Arrays.toString(arr2));
        moveZeroes(arr2);
        System.out.println("After:  " + Arrays.toString(arr2));

        // Test Case 3: All zeroes
        int[] arr3 = {0, 0, 0, 0};
        System.out.println("\nTest Case 3:");
        System.out.println("Before: " + Arrays.toString(arr3));
        moveZeroes(arr3);
        System.out.println("After:  " + Arrays.toString(arr3));
    }
}
