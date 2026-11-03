import java.util.*;

/**
 * Problem Statement:
 * You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
 * You must rearrange the elements of nums such that the modified array follows the given conditions:
 * 1. Every consecutive pair of integers have opposite signs.
 * 2. For all integers with the same sign, the order in which they were present in nums is preserved.
 * Return the modified array.
 *
 * Example Input & Output:
 * Input: nums = [3, 1, -2, -5, 2, -4]
 * Output: [3, -2, 1, -5, 2, -4]
 *
 * Intuition & Approach:
 * Since we know there are equal numbers of positive and negative integers, we can place positives
 * at even indices (0, 2, 4...) and negatives at odd indices (1, 3, 5...) using a separate result array.
 *
 * Time Complexity: O(N) to traverse and populate the result array.
 * Space Complexity: O(N) to store the rearranged result array.
 */
public class RearrangeArrayElementsBySign {
    public static int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int posIndex = 0, negIndex = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans[posIndex] = nums[i];
                posIndex += 2;
            } else {
                ans[negIndex] = nums[i];
                negIndex += 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("--- Running RearrangeArrayElementsBySign Tests ---");
        int[] test1 = {3, 1, -2, -5, 2, -4};
        System.out.println("Input: " + Arrays.toString(test1));
        System.out.println("Output: " + Arrays.toString(rearrangeArray(test1)));

        int[] test2 = {-1, 1};
        System.out.println("Input: " + Arrays.toString(test2));
        System.out.println("Output: " + Arrays.toString(rearrangeArray(test2)));
    }
}
