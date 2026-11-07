/**
 * Problem Statement:
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * Example 1:
 * Input: s = "aba"
 * Output: true
 *
 * Example 2:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 *
 * Example 3:
 * Input: s = "abc"
 * Output: false
 *
 * Intuition & Approach:
 * Standard palindrome check uses two pointers: left at index 0 and right at index (n - 1).
 * As long as s.charAt(left) == s.charAt(right), we increment left and decrement right.
 * When a mismatch occurs (s.charAt(left) != s.charAt(right)), we have two options:
 * 1. Delete s.charAt(left), meaning we check if substring from (left + 1) to right is a palindrome.
 * 2. Delete s.charAt(right), meaning we check if substring from left to (right - 1) is a palindrome.
 * If either candidate substring is a palindrome, the original string can form a palindrome with at most 1 deletion.
 * If both fail, it's impossible, so we return false.
 *
 * Time Complexity:
 * O(n) - We traverse the string once, and on the first mismatch, we verify at most two substrings of length <= n.
 *
 * Space Complexity:
 * O(1) - Constant extra space used for two pointers.
 */
public class ValidPalindromeII {

    /**
     * Determines whether s can be a palindrome after deleting at most one character.
     *
     * @param s input string
     * @return true if valid palindrome under at most one deletion, false otherwise
     */
    public static boolean validPalindrome(String s) {
        if (s == null) {
            return false;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try skipping either character at left or character at right
                return isPalindromeRange(s, left + 1, right) || isPalindromeRange(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * Helper method to check if substring s[left..right] is a strict palindrome.
     */
    private static boolean isPalindromeRange(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] testCases = {
            "aba",
            "abca",
            "abc",
            "racecar",
            "deeee",
            "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"
        };

        System.out.println("=== Valid Palindrome II ===");
        for (String test : testCases) {
            boolean result = validPalindrome(test);
            System.out.println("Input: \"" + (test.length() > 20 ? test.substring(0, 15) + "..." : test) + "\" -> Can be palindrome: " + result);
        }
    }
}
