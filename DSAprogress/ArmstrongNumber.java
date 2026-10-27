import java.util.*;

/**
 * Problem Statement:
 * Given an integer N, return true if it is an Armstrong number (an n-digit number that is the sum of the nth powers of its digits).
 * 
 * Example Input & Output:
 * Input: N = 153
 * Output: true
 * Explanation: 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153.
 * 
 * Intuition & Approach:
 * Count the number of digits, then extract each digit, raise it to the power of the count, and sum them up.
 * 
 * Time Complexity:
 * O(log10(N)) to process the digits.
 * 
 * Space Complexity:
 * O(1) constant auxiliary space.
 */
public class ArmstrongNumber {
    public static boolean isArmstrong(int n) {
        if (n < 0) return false;
        int original = n;
        int digits = String.valueOf(n).length();
        int sum = 0;
        
        while (n > 0) {
            int digit = n % 10;
            sum += Math.pow(digit, digits);
            n /= 10;
        }
        
        return sum == original;
    }

    public static void main(String[] args) {
        System.out.println("Running tests for ArmstrongNumber...");
        int[] testCases = {153, 9474, 123, 0};
        for (int n : testCases) {
            System.out.println("N = " + n + " -> Is Armstrong? " + isArmstrong(n));
        }
    }
}
