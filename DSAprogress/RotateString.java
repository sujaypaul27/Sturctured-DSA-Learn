/**
 * Problem Statement:
 * Given two strings 's' and 'goal', return true if and only if 's' can become 'goal' after
 * some number of shifts.
 * A shift on 's' consists of moving the leftmost character of 's' to the rightmost position.
 * For example, if s = "abcde", then it will be "bcdea" after one shift.
 *
 * Examples:
 * Input: s = "abcde", goal = "cdeab"
 * Output: true
 * Explanation: Rotating "abcde" by 2 shifts gives "cdeab".
 *
 * Input: s = "abcde", goal = "abced"
 * Output: false
 *
 * Intuition & Approach:
 * If 'goal' is a valid rotation of 's', both must have the same length.
 * Crucially, concatenating 's' with itself (s + s) contains every possible circular rotation
 * of 's' as a contiguous substring of length s.length().
 * Thus, after verifying lengths are equal, we just check if (s + s) contains 'goal'.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the length of string s. String concatenation takes O(N),
 *   and substring search (e.g. via KMP or built-in indexOf) takes O(N).
 * - Space Complexity: O(N) to store the concatenated string (s + s).
 */
public class RotateString {

    public static boolean rotateString(String s, String goal) {
        // Rotated strings must have identical lengths
        if (s.length() != goal.length()) {
            return false;
        }

        // All possible rotations of s are present as substrings in s + s
        String doubled = s + s;
        return doubled.contains(goal);
    }

    public static void main(String[] args) {
        String[][] testCases = {
            {"abcde", "cdeab"},
            {"abcde", "abced"},
            {"a", "a"},
            {"rotation", "tionrota"}
        };

        for (String[] test : testCases) {
            String s = test[0];
            String goal = test[1];
            boolean result = rotateString(s, goal);
            System.out.printf("s: \"%s\", goal: \"%s\" -> Is Rotation: %b%n", s, goal, result);
        }
    }
}
