import java.util.*;

/**
 * Problem Statement:
 * Given an integer N, find the sum of the first N natural numbers.
 * 
 * Example Input & Output:
 * Input: N = 5
 * Output: 15
 * Explanation: 1 + 2 + 3 + 4 + 5 = 15
 * 
 * Intuition & Approach:
 * We can calculate the sum iteratively in O(N) time or use the direct mathematical formula: (N * (N + 1)) / 2.
 * 
 * Time Complexity:
 * O(1) using the formula, or O(N) if computed iteratively. We will use the formula for efficiency.
 * 
 * Space Complexity:
 * O(1) as no extra memory is required.
 */
public class SumOfNumbers {
    public static long calculateSum(int n) {
        if (n < 0) return 0;
        return (long) n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        System.out.println("Running tests for SumOfNumbers...");
        int[] testCases = {5, 10, 100, 0};
        for (int n : testCases) {
            System.out.println("N = " + n + " -> Sum = " + calculateSum(n));
        }
    }
}
