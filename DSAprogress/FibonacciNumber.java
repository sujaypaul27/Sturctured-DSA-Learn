import java.util.*;

/**
 * Problem Statement:
 * The Fibonacci numbers, commonly denoted F(n), form a sequence such that each
 * number is the sum of the two preceding ones, starting from 0 and 1.
 * That is:
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given an integer n, calculate F(n).
 *
 * Examples:
 * Input: n = 2 -> Output: 1 (F(2) = F(1) + F(0) = 1 + 0 = 1)
 * Input: n = 4 -> Output: 3 (Sequence: 0, 1, 1, 2, 3)
 *
 * Intuition & Approach:
 * A naive recursive approach has exponential time complexity O(2^n) due to repeated
 * subproblem calculations. We can optimize this by using dynamic programming or simply
 * keeping track of the last two computed terms (bottom-up iterative approach).
 * We maintain two variables `prev2` and `prev1` initialized to 0 and 1 respectively.
 * We iterate from 2 up to n, computing the next term as `curr = prev1 + prev2`,
 * then update `prev2 = prev1` and `prev1 = curr`.
 *
 * Time Complexity: O(n) - Single loop iterating from 2 to n.
 * Space Complexity: O(1) - Only a few primitive variables used for state tracking.
 */
public class FibonacciNumber {

    /**
     * Computes the n-th Fibonacci number iteratively in O(1) space.
     *
     * @param n the index in the sequence (n >= 0)
     * @return the n-th Fibonacci number
     */
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int prev2 = 0;
        int prev1 = 1;

        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int[] testCases = {0, 1, 2, 5, 10, 20};

        System.out.println("=== Testing Fibonacci Number ===");
        for (int n : testCases) {
            int result = fib(n);
            System.out.printf("F(%d) = %d%n", n, result);
        }
    }
}
