/**
 * Problem Statement:
 * Seven different symbols represent Roman numerals with specific values:
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * Roman numerals are formed by appending conversions of decimal place values from highest to lowest.
 * Given an integer num (1 <= num <= 3999), convert it to a Roman numeral.
 *
 * Examples:
 * Input: num = 3749
 * Output: "MMMDCCXLIX"
 * Explanation: 3000 = MMM, 700 = DCC, 40 = XL, 9 = IX.
 *
 * Input: num = 58
 * Output: "LVIII"
 * Explanation: 50 = L, 8 = VIII.
 *
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation: 1000 = M, 900 = CM, 90 = XC, 4 = IV.
 *
 * Intuition & Approach:
 * A greedy approach works best because Roman numeral values are fixed and finite.
 * We can list all standard values and subtractive pairings (e.g. 1000, 900, 500, 400, ..., 1)
 * in descending order alongside their Roman representations.
 * For each value, as long as 'num' is greater than or equal to that value, we append the symbol
 * to our result and subtract the value from 'num'.
 *
 * Complexity Analysis:
 * - Time Complexity: O(1) because the number is bounded (1 <= num <= 3999), and the number of loop iterations
 *   is strictly bounded by a constant.
 * - Space Complexity: O(1) auxiliary space (excluding the output StringBuilder).
 */
public class IntegerToRoman {

    // Parallel arrays for greedy subtraction
    private static final int[] VALUES = {
        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };
    private static final String[] SYMBOLS = {
        "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    public static String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < VALUES.length && num > 0; i++) {
            while (num >= VALUES[i]) {
                roman.append(SYMBOLS[i]);
                num -= VALUES[i];
            }
        }

        return roman.toString();
    }

    public static void main(String[] args) {
        int[] testCases = {3, 58, 1994, 3749};

        for (int num : testCases) {
            String roman = intToRoman(num);
            System.out.printf("Integer: %-5d -> Roman: %s%n", num, roman);
        }
    }
}
