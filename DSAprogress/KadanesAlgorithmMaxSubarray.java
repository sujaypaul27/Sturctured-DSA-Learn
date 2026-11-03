import java.util.*;

/**
 * Problem Statement:
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example Input & Output:
 * Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output: 6
 * Explanation: [4, -1, 2, 1] has the largest sum = 6.
 *
 * Intuition & Approach:
 * Kadane's Algorithm maintains a running sum of the current subarray (currentSum).
 * If currentSum drops below 0, it's better to discard it and start fresh from the next element.
 * At each step, we update the global maximum sum (maxSum).
 *
 * Time Complexity: O(N) as we traverse the array once.
 * Space Complexity: O(1) since only a couple of variables are used.
 */
public class KadanesAlgorithmMaxSubarray {
    public static int maxSubArray(int[] nums) {
        long maxSum = Long.MIN_VALUE;
        long currentSum = 0;
        for (int num : nums) {
            currentSum += num;
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return (int) maxSum;
    }

    public static void main(String[] args) {
        System.out.println("--- Running KadanesAlgorithmMaxSubarray Tests ---");
        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("Maximum Subarray Sum: " + maxSubArray(test1));

        int[] test2 = {1};
        System.out.println("Input: [1]");
        System.out.println("Maximum Subarray Sum: " + maxSubArray(test2));
    }
}
