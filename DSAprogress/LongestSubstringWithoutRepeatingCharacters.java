import java.util.HashMap;
import java.util.Map;

/**
 * Problem Statement:
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Intuition & Approach:
 * We can solve this efficiently using the Sliding Window technique with two pointers (left and right).
 * As we expand the window using the right pointer, we keep track of the most recent index where each
 * character appeared using a hash map or an array. If a character at the right pointer has already
 * been seen and its previous index is within the current window (>= left), we jump the left pointer
 * to (previous index + 1) to eliminate the duplicate.
 * At each step, the length of the valid window is (right - left + 1), and we update the maximum length found.
 *
 * Time Complexity:
 * O(n) - We iterate through the string of length n once with the right pointer.
 *
 * Space Complexity:
 * O(min(m, n)) - Where n is the length of the string and m is the size of the character set (at most 128 for ASCII).
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * @param s the input string
     * @return maximum length of substring with unique characters
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If character is already in window, jump left pointer past previous occurrence
            if (lastSeen.containsKey(currentChar)) {
                left = Math.max(left, lastSeen.get(currentChar) + 1);
            }

            lastSeen.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String[] testCases = {
            "abcabcbb",
            "bbbbb",
            "pwwkew",
            "",
            "abba"
        };

        System.out.println("=== Longest Substring Without Repeating Characters ===");
        for (String test : testCases) {
            int result = lengthOfLongestSubstring(test);
            System.out.println("Input: \"" + test + "\" -> Longest Unique Substring Length: " + result);
        }
    }
}
