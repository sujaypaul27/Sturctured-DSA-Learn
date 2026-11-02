import java.util.Arrays;

/**
 * Problem Statement:
 * Given a binary array `nums`, return the maximum number of consecutive 1's in the array.
 *
 * Example 1:
 * Input: nums = [1, 1, 0, 1, 1, 1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 * The maximum number of consecutive 1s is 3.
 *
 * Example 2:
 * Input: nums = [1, 0, 1, 1, 0, 1]
 * Output: 2
 *
 * Intuition & Approach:
 * We maintain two variables:
 * 1. `currentCount`: Tracks the length of the current streak of 1s.
 * 2. `maxCount`: Keeps track of the maximum streak seen so far.
 * Iterate through each element in the array:
 * - If the element is 1, increment `currentCount` and update `maxCount`.
 * - If the element is 0, reset `currentCount` to 0 because the streak of 1s is broken.
 *
 * Time Complexity:
 * O(n) - We only make a single linear pass over the array of size n.
 *
 * Space Complexity:
 * O(1) - Only a couple of integer counters are maintained.
 */
public class MaxConsecutiveOnes {

    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;

        for (int num : nums) {
            if (num == 1) {
                currentCount++;
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                }
            } else {
                // Streak broken by 0
                currentCount = 0;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 1, 0, 1, 1, 1};
        int[] test2 = {1, 0, 1, 1, 0, 1};
        int[] test3 = {0, 0, 0};
        int[] test4 = {1, 1, 1, 1};

        System.out.println("Test Case 1: " + Arrays.toString(test1));
        System.out.println("Max consecutive ones: " + findMaxConsecutiveOnes(test1));
        System.out.println();

        System.out.println("Test Case 2: " + Arrays.toString(test2));
        System.out.println("Max consecutive ones: " + findMaxConsecutiveOnes(test2));
        System.out.println();

        System.out.println("Test Case 3: " + Arrays.toString(test3));
        System.out.println("Max consecutive ones: " + findMaxConsecutiveOnes(test3));
        System.out.println();

        System.out.println("Test Case 4: " + Arrays.toString(test4));
        System.out.println("Max consecutive ones: " + findMaxConsecutiveOnes(test4));
    }
}
