import java.util.*;

/**
 * Problem Statement:
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than floor(n / 2) times.
 * You may assume that the majority element always exists in the array.
 *
 * Example Input & Output:
 * Input: nums = [3, 2, 3]
 * Output: 3
 * Explanation: 3 appears 2 times, which is > 3/2 = 1.
 *
 * Intuition & Approach:
 * We use Boyer-Moore Voting Algorithm. We maintain a candidate and a count.
 * When count becomes 0, we pick the current element as the new candidate.
 * If the current element matches the candidate, we increment count, else decrement.
 *
 * Time Complexity: O(N) because we iterate through the array once.
 * Space Complexity: O(1) since we only use a few variables for counting.
 */
public class MajorityElement {
    public static int findMajorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        System.out.println("--- Running MajorityElement Tests ---");
        int[] test1 = {3, 2, 3};
        System.out.println("Input: [3, 2, 3]");
        System.out.println("Majority Element: " + findMajorityElement(test1));

        int[] test2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Input: [2, 2, 1, 1, 1, 2, 2]");
        System.out.println("Majority Element: " + findMajorityElement(test2));
    }
}
