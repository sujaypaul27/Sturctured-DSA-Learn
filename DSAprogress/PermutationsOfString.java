import java.util.*;

/**
 * Problem Statement:
 * Given a string S, return all distinct permutations of the characters in S.
 * A permutation is a rearrangement of all elements in a specific order.
 *
 * Example 1:
 * Input: S = "ABC"
 * Output: ["ABC", "ACB", "BAC", "BCA", "CAB", "CBA"]
 *
 * Example 2:
 * Input: S = "AB"
 * Output: ["AB", "BA"]
 *
 * Intuition & Approach:
 * We use recursive swapping (backtracking):
 * - Fix one character at index `idx` by swapping it with each character from index `idx` to end.
 * - Recurse for the next index `idx + 1`.
 * - Undo the swap (backtrack) to restore the string for the next branch.
 * - To avoid duplicates when characters repeat, we can use a Set at each level.
 *
 * Time Complexity:
 * O(n * n!) where n is the length of the string. There are n! permutations, and creating string takes O(n).
 *
 * Space Complexity:
 * O(n) recursive call stack depth, plus storage for results.
 */
public class PermutationsOfString {

    public static List<String> findPermutations(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }
        char[] chars = str.toCharArray();
        permute(chars, 0, result);
        Collections.sort(result);
        return result;
    }

    private static void permute(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }

        // Track elements used at current recursion level to prevent duplicates
        Set<Character> seen = new HashSet<>();
        for (int i = index; i < chars.length; i++) {
            if (!seen.contains(chars[i])) {
                seen.add(chars[i]);
                swap(chars, index, i);
                permute(chars, index + 1, result);
                swap(chars, index, i); // Backtrack
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        // Test Case 1: Simple 3-letter word
        String s1 = "ABC";
        System.out.println("Test Case 1:");
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Permutations: " + findPermutations(s1));
        System.out.println();

        // Test Case 2: String with duplicates
        String s2 = "ABA";
        System.out.println("Test Case 2 (with duplicate letters):");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Permutations: " + findPermutations(s2));
        System.out.println();

        // Test Case 3: Single character
        String s3 = "X";
        System.out.println("Test Case 3:");
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Permutations: " + findPermutations(s3));
    }
}
