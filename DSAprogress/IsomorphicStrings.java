import java.util.Arrays;

/**
 * Problem Statement:
 * Given two strings 's' and 't', determine if they are isomorphic.
 * Two strings are isomorphic if characters in 's' can be replaced to get 't'.
 * All occurrences of a character must be replaced with another character while preserving
 * the order of characters. No two characters may map to the same character, but a character
 * may map to itself.
 *
 * Examples:
 * Input: s = "egg", t = "add"
 * Output: true
 * Explanation: 'e' maps to 'a', 'g' maps to 'd'.
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Explanation: 'o' cannot map to both 'a' and 'r'.
 *
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * Intuition & Approach:
 * A bijection (one-to-one and onto mapping) is required between characters of 's' and 't'.
 * We can record the last seen index (+ 1 to differentiate from unvisited default 0) of each character
 * using two arrays/tables of size 256 (ASCII standard).
 * If at any position the previous positions of s.charAt(i) and t.charAt(i) differ, the strings
 * are not isomorphic.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the length of strings s and t. We do a single pass over the strings.
 * - Space Complexity: O(1), fixed-size arrays of 256 integers are used irrespective of input string length.
 */
public class IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // Store last seen index (+1) of characters in s and t
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // If the last seen positions do not match, mapping is inconsistent
            if (mapS[charS] != mapT[charT]) {
                return false;
            }

            // Store (i + 1) to distinguish from the default initial value 0
            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] testCases = {
            {"egg", "add"},
            {"foo", "bar"},
            {"paper", "title"},
            {"badc", "baba"}
        };

        for (String[] test : testCases) {
            String s = test[0];
            String t = test[1];
            boolean result = isIsomorphic(s, t);
            System.out.printf("s: \"%s\", t: \"%s\" -> Isomorphic: %b%n", s, t, result);
        }
    }
}
