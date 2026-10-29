package math;

import java.util.*;

/**
 * Problem Statement:
 * Given a non-negative decimal (base-10) integer N, convert it into its binary
 * (base-2) string representation.
 *
 * Examples:
 * Input: 13
 * Output: "1101"
 * Explanation: 13 = 8 + 4 + 1 = 1101 in base 2.
 *
 * Input: 7
 * Output: "111"
 * Explanation: 7 = 4 + 2 + 1 = 111 in base 2.
 *
 * Intuition & Approach:
 * 1. Base case: If N is 0, the binary representation is simply "0".
 * 2. Repeated division by 2: While N > 0, the remainder of N % 2 gives the least significant bit.
 *    Append this bit to a StringBuilder, then divide N by 2 (or bit-shift right by 1).
 * 3. After the loop, reverse the accumulated characters because bits were collected from
 *    least significant to most significant.
 *
 * Time Complexity:
 * O(log2(N)) - The number of divisions by 2 corresponds to the number of bits in N.
 *
 * Space Complexity:
 * O(log2(N)) - To store the output characters in the StringBuilder.
 */
public class DecimalToBinary {

    /**
     * Converts a non-negative integer to its binary string representation using division/modulo.
     */
    public static String toBinary(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("Only non-negative numbers supported for standard binary conversion.");
        }
        if (n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 2);
            n /= 2;
        }
        return sb.reverse().toString();
    }

    /**
     * Alternative bitwise conversion method.
     */
    public static String toBinaryBitwise(int n) {
        if (n == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append((n & 1));
            n >>= 1;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        long[] testCases = {0, 7, 13, 42, 255};

        System.out.println("--- Decimal to Binary Conversion ---");
        for (long val : testCases) {
            String binStr = toBinary(val);
            System.out.println("Decimal: " + val + " -> Binary: " + binStr);
        }
    }
}
