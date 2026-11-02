import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array `nums` containing `n` distinct numbers in the range `[0, n]`,
 * return the only number in the range that is missing from the array.
 *
 * Example 1:
 * Input: nums = [3, 0, 1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0, 3].
 * 2 is the missing number in the range since it does not appear in nums.
 *
 * Example 2:
 * Input: nums = [0, 1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers. 2 is missing from [0, 2].
 *
 * Intuition & Approach:
 * Approach 1 (Gauss Sum formula):
 * The sum of the first n natural numbers is given by n * (n + 1) / 2.
 * If we subtract each element present in the array from this expected total sum,
 * the remaining value must be the missing number.
 * Caveat: Sum calculation might overflow for extremely large values of n, though for standard constraints it's fine.
 *
 * Approach 2 (XOR Trick - Optimal & Overflow safe):
 * XOR of any number with itself is 0, and XOR of any number with 0 is the number itself.
 * If we compute XOR of all numbers from 0 to n, and XOR all elements present in the array,
 * duplicate numbers will cancel out, leaving only the missing number.
 *
 * Time Complexity:
 * O(n) - Single pass through the array of length n.
 *
 * Space Complexity:
 * O(1) - Only constant extra variables used.
 */
public class MissingNumberInArray {

    // Approach using XOR bit manipulation
    public static int findMissingNumberXOR(int[] nums) {
        int n = nums.length;
        int xor = 0;

        // XOR all indices and elements
        for (int i = 0; i < n; i++) {
            xor ^= i ^ nums[i];
        }

        // XOR with the last value n
        xor ^= n;
        return xor;
    }

    // Approach using Mathematical Sum Formula
    public static int findMissingNumberSum(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] test1 = {3, 0, 1};
        int[] test2 = {0, 1};
        int[] test3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.toString(test1));
        System.out.println("Missing (XOR): " + findMissingNumberXOR(test1));
        System.out.println("Missing (Sum): " + findMissingNumberSum(test1));
        System.out.println();

        System.out.println("Test Case 2:");
        System.out.println("Input: " + Arrays.toString(test2));
        System.out.println("Missing (XOR): " + findMissingNumberXOR(test2));
        System.out.println("Missing (Sum): " + findMissingNumberSum(test2));
        System.out.println();

        System.out.println("Test Case 3:");
        System.out.println("Input: " + Arrays.toString(test3));
        System.out.println("Missing (XOR): " + findMissingNumberXOR(test3));
        System.out.println("Missing (Sum): " + findMissingNumberSum(test3));
    }
}
