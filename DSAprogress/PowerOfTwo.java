import java.util.*;

/**
 * Problem Statement:
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 * An integer n is a power of two if there exists an integer x such that n == 2^x.
 *
 * Examples:
 * Input: n = 1  -> Output: true (2^0 = 1)
 * Input: n = 16 -> Output: true (2^4 = 16)
 * Input: n = 3  -> Output: false
 * Input: n = 0  -> Output: false
 * Input: n = -16 -> Output: false
 *
 * Intuition & Approach:
 * In binary representation, a positive power of two has exactly one set bit (e.g., 1 -> 0001, 2 -> 0010, 4 -> 0100, 8 -> 1000).
 * When we subtract 1 from a power of two, all bits from the rightmost set bit invert (e.g., 4 = 100_2, 4 - 1 = 3 = 011_2).
 * Performing a bitwise AND between `n` and `n - 1` clears the lowest set bit:
 * - If `n` is a power of two, `(n & (n - 1))` equals 0.
 * - We also must ensure `n > 0` since non-positive numbers (including 0 and negative integers like Integer.MIN_VALUE) cannot be powers of two.
 *
 * Time Complexity: O(1) - Single bitwise operation.
 * Space Complexity: O(1) - Constant auxiliary space.
 */
public class PowerOfTwo {

    /**
     * Determines whether n is a power of two using bit manipulation.
     *
     * @param n the input integer
     * @return true if n is a power of two, false otherwise
     */
    public static boolean isPowerOfTwo(int n) {
        // A power of two must be positive and have exactly one set bit
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        int[] testCases = {1, 2, 16, 18, 0, -4, 1024};

        System.out.println("=== Testing Power of Two ===");
        for (int n : testCases) {
            boolean result = isPowerOfTwo(n);
            System.out.printf("Is %d a power of two? -> %b%n", n, result);
        }
    }
}
