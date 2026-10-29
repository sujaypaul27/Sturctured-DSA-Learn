package math;

import java.util.*;

/**
 * Problem Statement:
 * Given an integer representing a year, determine whether it is a leap year or not.
 *
 * Examples:
 * Input: 2024
 * Output: true
 * Explanation: 2024 is divisible by 4 and not divisible by 100, hence it is a leap year.
 *
 * Input: 1900
 * Output: false
 * Explanation: 1900 is divisible by 100 but not divisible by 400, hence it is NOT a leap year.
 *
 * Input: 2000
 * Output: true
 * Explanation: 2000 is divisible by 400, hence it is a leap year.
 *
 * Intuition & Approach:
 * A year is a leap year in the Gregorian calendar if:
 * 1. It is divisible by 400 -> Leap Year
 * 2. It is divisible by 100 -> Not a Leap Year
 * 3. It is divisible by 4 -> Leap Year
 * 4. Otherwise -> Not a Leap Year
 * Combining these gives the classic formula: (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0).
 *
 * Time Complexity:
 * O(1) - Constant time arithmetic checks.
 *
 * Space Complexity:
 * O(1) - No auxiliary space used.
 */
public class LeapYearCheck {

    /**
     * Checks whether the given year is a leap year according to Gregorian calendar rules.
     */
    public static boolean isLeapYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be a positive integer.");
        }
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    public static void main(String[] args) {
        int[] testYears = {2000, 2024, 1900, 2023, 2028, 2100};

        System.out.println("--- Leap Year Check ---");
        for (int year : testYears) {
            boolean result = isLeapYear(year);
            System.out.printf("Year %d: %s%n", year, result ? "Leap Year" : "Common Year");
        }
    }
}
