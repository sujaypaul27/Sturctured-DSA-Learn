import java.util.HashMap;
import java.util.Map;

/**
 * Problem Statement:
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * Given a roman numeral string, convert it to an integer.
 *
 * Examples:
 * Input: s = "III"
 * Output: 3
 *
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V = 5, III = 3.
 *
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * Intuition & Approach:
 * Roman numerals are usually written largest to smallest from left to right.
 * However, when a smaller numeral appears before a larger one (e.g. IV = 4, IX = 9),
 * it represents subtraction rather than addition.
 * We can scan the string from left to right: if current character's value is less than the
 * next character's value, we subtract it; otherwise, we add it.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the length of the string s. We iterate through the string once.
 * - Space Complexity: O(1) auxiliary space using a fast lookup helper function.
 */
public class RomanToInteger {

    private static int charValue(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static int romanToInt(String s) {
        int total = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int currentVal = charValue(s.charAt(i));

            // If next value is larger, current value is subtracted (e.g., IV = 5 - 1 = 4)
            if (i + 1 < n && currentVal < charValue(s.charAt(i + 1))) {
                total -= currentVal;
            } else {
                total += currentVal;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        String[] testCases = {"III", "LVIII", "MCMXCIV", "XLII"};

        for (String roman : testCases) {
            int val = romanToInt(roman);
            System.out.printf("Roman: %-10s -> Integer: %d%n", roman, val);
        }
    }
}
