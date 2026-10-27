import java.util.*;

/**
 * Problem Statement:
 * Given an integer N, return the number of digits present in N.
 * 
 * Example Input & Output:
 * Input: N = 4521
 * Output: 4
 * Explanation: The number 4521 has 4 digits.
 * 
 * Intuition & Approach:
 * Repeatedly divide the number by 10 until it becomes 0, counting the divisions. Alternatively, use logarithms.
 * 
 * Time Complexity:
 * O(log10(N)) because we divide the number by 10 in each step.
 * 
 * Space Complexity:
 * O(1) since we only use a counter variable.
 */
public class CountDigits {
    public static int count(int n) {
        if (n == 0) return 1;
        int count = 0;
        int num = Math.abs(n);
        while (num > 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Running tests for CountDigits...");
        int[] testCases = {4521, 0, -999, 100000};
        for (int n : testCases) {
            System.out.println("N = " + n + " -> Digits = " + count(n));
        }
    }
}
