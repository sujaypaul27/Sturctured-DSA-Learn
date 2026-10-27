import java.util.*;

/**
 * Problem Statement:
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * 
 * Example Input & Output:
 * Input: x = 123
 * Output: 321
 * 
 * Intuition & Approach:
 * Extract the last digit using modulo 10, append it to the reversed result, and multiply the result by 10.
 * 
 * Time Complexity:
 * O(log10(x)) as we process each digit.
 * 
 * Space Complexity:
 * O(1) as we use constant extra space.
 */
public class ReverseNumber {
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            
            // Check for overflow/underflow
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) return 0;
            
            rev = rev * 10 + digit;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println("Running tests for ReverseNumber...");
        int[] testCases = {123, -456, 120, 0};
        for (int x : testCases) {
            System.out.println("Original = " + x + " -> Reversed = " + reverse(x));
        }
    }
}
