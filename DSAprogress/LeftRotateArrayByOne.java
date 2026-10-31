import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of integers, rotate the array to the left by one position in-place.
 * Left rotating by one position means shifting all elements one index to the left,
 * and moving the first element to the end of the array.
 *
 * Example 1:
 * Input: nums = [1, 2, 3, 4, 5]
 * Output: [2, 3, 4, 5, 1]
 *
 * Example 2:
 * Input: nums = [-1, 10, 20]
 * Output: [10, 20, -1]
 *
 * Intuition & Approach:
 * 1. Store the first element (nums[0]) in a temporary variable 'first'.
 * 2. Shift all elements from index 1 to n - 1 one step to the left:
 *    nums[i - 1] = nums[i] for each i from 1 up to n - 1.
 * 3. Assign the saved 'first' element to the last index nums[n - 1].
 * This modifies the array in-place cleanly without allocating any extra buffer.
 *
 * Time Complexity:
 * O(N) where N is the length of the array, as every element is shifted once.
 *
 * Space Complexity:
 * O(1) auxiliary space, using only a single scalar variable for the displaced first element.
 */
public class LeftRotateArrayByOne {

    public static void rotateLeftByOne(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int first = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i - 1] = nums[i];
        }
        nums[nums.length - 1] = first;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5};
        System.out.println("Before rotation: " + Arrays.toString(test1));
        rotateLeftByOne(test1);
        System.out.println("After 1 left rotation: " + Arrays.toString(test1));
        System.out.println();

        int[] test2 = {-1, 10, 20};
        System.out.println("Before rotation: " + Arrays.toString(test2));
        rotateLeftByOne(test2);
        System.out.println("After 1 left rotation: " + Arrays.toString(test2));
        System.out.println();

        int[] test3 = {42};
        System.out.println("Before rotation: " + Arrays.toString(test3));
        rotateLeftByOne(test3);
        System.out.println("After 1 left rotation: " + Arrays.toString(test3));
    }
}
