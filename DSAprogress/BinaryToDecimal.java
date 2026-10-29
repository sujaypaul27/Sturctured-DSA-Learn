package math;

import java.util.*;

/**
 * Problem Statement:
 * Given a binary number represented either as a string of '0's and '1's or as an integer,
 * convert it into its equivalent decimal (base-10) integer representation.
 *
 * Examples:
 * Input: "1101"
 * Output: 13
 * Explanation: 1*(2^3) + 1*(2^2) + 0*(2^1) + 1*(2^0) = 8 + 4 + 0 + 1 = 13.
 *
 * Input: "1010"
 * Output: 10
 * Explanation: 1*(2^3) + 0*(2^2) + 1*(2^1) + 0*(2^0) = 8 + 0 + 2 + 0 = 10.
 *
 * Intuition & Approach:
 * We can process the binary string from left to right (or right to left).
 * A clean Horner's-rule-like approach traverses from left to right:
 * Maintain an accumulator result = 0. For each bit encountered, multiply result by 2
 * and add the bit value (bit - '0'). This avoids explicit power computations.
 *
 * Time Complexity:
 * O(N) where N is the number of bits in the binary representation.
 * Each character is processed once in constant time.
 *
 * Space Complexity:
 * O(1) auxiliary space as we only use a few integer variables for calculation.
 */
public class BinaryToDecimal {

    /**
     * Converts a binary string to its decimal equivalent.
     */
    public static long toDecimal(String binaryStr) {
        if (binaryStr == null || binaryStr.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        long decimalValue = 0;
        for (int i = 0; i < binaryStr.length(); i++) {
            char ch = binaryStr.charAt(i);
            if (ch != '0' && ch != '1') {
                throw new IllegalArgumentException("Invalid binary digit: " + ch);
            }
            decimalValue = (decimalValue << 1) | (ch - '0');
        }
        return decimalValue;
    }

    /**
     * Converts a binary number passed as a long integer (e.g., 1101) to decimal.
     */
    public static long toDecimal(long binaryNumber) {
        long decimalValue = 0;
        long base = 1;
        long temp = binaryNumber;

        while (temp > 0) {
            long lastDigit = temp % 10;
            if (lastDigit != 0 && lastDigit != 1) {
                throw new IllegalArgumentException("Invalid binary digit: " + lastDigit);
            }
            decimalValue += lastDigit * base;
            base *= 2;
            temp /= 10;
        }
        return decimalValue;
    }

    public static void main(String[] args) {
        String[] testStrings = {"1101", "1010", "11111111", "0"};
        System.out.println("--- Binary to Decimal Conversion (String Input) ---");
        for (String bin : testStrings) {
            System.out.println("Binary: " + bin + " -> Decimal: " + toDecimal(bin));
        }

        long[] testInts = {1101L, 1010L, 100000L};
        System.out.println("\n--- Binary to Decimal Conversion (Integer Input) ---");
        for (long num : testInts) {
            System.out.println("Binary: " + num + " -> Decimal: " + toDecimal(num));
        }
    }
}
