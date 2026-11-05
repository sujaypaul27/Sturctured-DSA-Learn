/**
 * Problem Statement:
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Example Input & Output:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Intuition & Approach:
 * Assume the first string in the array is the longest common prefix initially.
 * Compare this prefix with each subsequent string in the array. Shrink the prefix from the end
 * (using indexOf) until it matches the beginning of the current string or becomes empty.
 *
 * Time Complexity: O(S) where S is the sum of all characters in all strings in the worst case.
 * Space Complexity: O(1) auxiliary space beyond storing the prefix reference.
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        System.out.println("--- Running LongestCommonPrefix Tests ---");
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("Result: \"" + longestCommonPrefix(strs1) + "\"");

        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("Result: \"" + longestCommonPrefix(strs2) + "\"");

        String[] strs3 = {"interspecies", "interstellar", "interstate"};
        System.out.println("Result: \"" + longestCommonPrefix(strs3) + "\"");
    }
}
