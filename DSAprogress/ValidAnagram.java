/**
 * Problem Statement:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example Input & Output:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Intuition & Approach:
 * If lengths of s and t differ, they cannot be anagrams. Otherwise, use an integer array
 * of size 26 to count character frequencies. Increment frequency for characters in s and
 * decrement for characters in t. If all counts remain zero, they are anagrams.
 *
 * Time Complexity: O(N) where N is the length of the strings, iterating through them to update counts.
 * Space Complexity: O(1) because the frequency array size is fixed at 26 regardless of input size.
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
            charCount[t.charAt(i) - 'a']--;
        }

        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("--- Running ValidAnagram Tests ---");
        String s1 = "anagram", t1 = "nagaram";
        System.out.println("s: \"" + s1 + "\", t: \"" + t1 + "\"");
        System.out.println("Is Anagram? " + isAnagram(s1, t1));

        String s2 = "rat", t2 = "car";
        System.out.println("s: \"" + s2 + "\", t: \"" + t2 + "\"");
        System.out.println("Is Anagram? " + isAnagram(s2, t2));
    }
}
