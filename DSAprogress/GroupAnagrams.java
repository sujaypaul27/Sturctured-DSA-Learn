import java.util.*;

/**
 * Problem Statement:
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 * Intuition & Approach:
 * Two strings are anagrams if and only if their sorted versions are identical, or if their character
 * frequency counts are identical.
 * We can use a HashMap where the key is the sorted version of the string (canonical representation),
 * and the value is a list of strings that match this sorted key.
 * We iterate through each string, convert it to a char array, sort it, and use the sorted string as
 * the map key. Finally, the values of the map give us the grouped anagrams.
 *
 * Time Complexity:
 * O(N * K log K) - Where N is the number of strings and K is the maximum length of a string in strs.
 * Sorting each string takes O(K log K).
 *
 * Space Complexity:
 * O(N * K) - To store all strings and their sorted keys in the hash map.
 */
public class GroupAnagrams {

    /**
     * Groups strings that are anagrams of each other.
     *
     * @param strs array of strings
     * @return list of grouped anagrams
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedKey = new String(chars);

            map.computeIfAbsent(sortedKey, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[][] testCases = {
            {"eat", "tea", "tan", "ate", "nat", "bat"},
            {""},
            {"a"},
            {"listen", "silent", "enlist", "google", "gogole"}
        };

        System.out.println("=== Group Anagrams ===");
        for (String[] test : testCases) {
            List<List<String>> grouped = groupAnagrams(test);
            System.out.println("Input: " + Arrays.toString(test));
            System.out.println("Grouped: " + grouped);
            System.out.println();
        }
    }
}
