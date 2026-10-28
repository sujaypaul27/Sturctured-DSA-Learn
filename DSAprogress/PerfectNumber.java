import java.util.*;

/**
 * Problem Statement:
 * A perfect number is a positive integer that is equal to the sum of its positive
 * proper divisors, excluding the number itself. A divisor of an integer x is an
 * integer that can divide x evenly.
 * Given an integer num, return true if num is a perfect number, otherwise return false.
 *
 * Examples:
 * Input: num = 28 -> Output: true
 * Explanation: 28 = 1 + 2 + 4 + 7 + 14 (divisors of 28 excluding 28).
 * Input: num = 7  -> Output: false
 * Explanation: Divisors of 7 are 1. Sum = 1 != 7.
 *
 * Intuition & Approach:
 * If we iterate up to num - 1 to find all divisors, it takes O(num) time, which will TLE.
 * Notice divisors always appear in pairs: if `i` divides `num`, then `num / i` also divides `num`.
 * Hence, we only need to iterate `i` from 2 up to `sqrt(num)`.
 * - If `i` divides `num`, add both `i` and `num / i` to the running sum.
 * - If `i == num / i`, add `i` only once.
 * - Remember to include 1 initially (if num > 1), because 1 is always a proper divisor.
 * Any number <= 1 cannot be a perfect number.
 *
 * Time Complexity: O(sqrt(num)) - Only iterate up to the square root of num.
 * Space Complexity: O(1) - Constant auxiliary space.
 */
public class PerfectNumber {

    /**
     * Checks whether a given number is a perfect number.
     *
     * @param num positive integer to check
     * @return true if num is perfect, false otherwise
     */
    public static boolean checkPerfectNumber(int num) {
        if (num <= 1) {
            return false;
        }

        int sum = 1; // 1 is always a proper divisor for num > 1

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                // If the divisors are distinct, add the paired divisor
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }

        return sum == num;
    }

    public static void main(String[] args) {
        int[] testCases = {6, 28, 496, 8128, 12, 1, 2};

        System.out.println("=== Testing Perfect Number ===");
        for (int num : testCases) {
            boolean isPerfect = checkPerfectNumber(num);
            System.out.printf("Is %d a perfect number? -> %b%n", num, isPerfect);
        }
    }
}
