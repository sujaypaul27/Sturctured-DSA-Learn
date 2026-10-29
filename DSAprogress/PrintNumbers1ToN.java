package math;

import java.util.*;

/**
 * Problem Statement:
 * Given an integer N, print all numbers from 1 to N in increasing order without using
 * any iterative loops (for, while, do-while).
 *
 * Examples:
 * Input: N = 5
 * Output: 1 2 3 4 5
 *
 * Input: N = 1
 * Output: 1
 *
 * Intuition & Approach:
 * Since loops are disallowed, we use recursion. To print from 1 up to N using head recursion:
 * 1. Base case: If N <= 0, simply return.
 * 2. Recursive call: First call print1ToN(N - 1) to print smaller values first.
 * 3. Processing step: Once the recursive call unwinds, print N followed by a space.
 * This guarantees output is printed in strictly ascending order from 1 to N.
 *
 * Alternatively, we can collect results into a List<Integer> to make the function pure
 * and easily testable.
 *
 * Time Complexity:
 * O(N) - There are N recursive calls, each doing O(1) work.
 *
 * Space Complexity:
 * O(N) - Depth of the recursion call stack is N.
 */
public class PrintNumbers1ToN {

    /**
     * Prints numbers from 1 to n directly to the console using recursion.
     */
    public static void print1ToN(int n) {
        if (n <= 0) {
            return;
        }
        print1ToN(n - 1);
        System.out.print(n + " ");
    }

    /**
     * Returns a list of numbers from 1 to n recursively without loops.
     */
    public static List<Integer> getNumbers1ToN(int n) {
        List<Integer> result = new ArrayList<>();
        collect(n, result);
        return result;
    }

    private static void collect(int n, List<Integer> result) {
        if (n <= 0) {
            return;
        }
        collect(n - 1, result);
        result.add(n);
    }

    public static void main(String[] args) {
        int[] testCases = {5, 10, 1};

        System.out.println("--- Recursive Print from 1 to N ---");
        for (int n : testCases) {
            System.out.print("N = " + n + ": ");
            print1ToN(n);
            System.out.println();
        }

        System.out.println("\n--- Verification with Collected Lists ---");
        for (int n : testCases) {
            System.out.println("getNumbers1ToN(" + n + ") -> " + getNumbers1ToN(n));
        }
    }
}
