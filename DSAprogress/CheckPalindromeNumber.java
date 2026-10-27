import java.util.*;

/**
 * Problem Statement:
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * 
 * Example Input & Output:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * 
 * Intuition & Approach:
 * Negative numbers are never palindromes. For positive numbers, we can reverse the entire number and check if it equals the original.
 * 
 * Time Complexity:
 * O(log10(x)) to reverse the number.
 * 
 * Space Complexity:
 * O(1) constant extra space.
 */
public class CheckPalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int original = x;
        int reversed = 0;
        while (x > 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }
        return original == reversed;
    }

    public static void main(String[] args) {
        System.out.println("Running tests for CheckPalindromeNumber...");
        int[] testCases = {121, -121, 10, 12321};
        for (int x : testCases) {
            System.out.println("x = " + x + " -> Is Palindrome? " + isPalindrome(x));
        }
    }
}
