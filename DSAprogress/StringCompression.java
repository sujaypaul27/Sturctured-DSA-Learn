import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of characters 'chars', compress it using the following algorithm:
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * - If the group's length is 1, append the character to s.
 * - Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned directly, but instead be stored in the input
 * character array 'chars'. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * After you are done modifying the input array, return the new length of the array.
 * You must write an algorithm that uses only O(1) extra space.
 *
 * Examples:
 * Input: chars = ['a','a','b','b','c','c','c']
 * Output: Return 6, and the first 6 characters of chars become ["a","2","b","2","c","3"].
 *
 * Input: chars = ['a']
 * Output: Return 1, chars becomes ["a"].
 *
 * Input: chars = ['a','b','b','b','b','b','b','b','b','b','b','b','b']
 * Output: Return 4, chars becomes ["a","b","1","2"].
 *
 * Intuition & Approach:
 * We use a two-pointer technique:
 * - 'read' scans through the array to find consecutive runs of the same character.
 * - 'write' writes the character and its frequency count directly back into 'chars'.
 * When a streak of identical characters ends, we write the character. If the count > 1,
 * we convert the count to string/characters and write each digit.
 * Because compressed length is always <= original length, 'write' never overtakes 'read'.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the length of the chars array. Both pointers advance monotonically.
 * - Space Complexity: O(1) auxiliary space as the modification is done in-place.
 */
public class StringCompression {

    public static int compress(char[] chars) {
        int write = 0;
        int read = 0;
        int n = chars.length;

        while (read < n) {
            char currentChar = chars[read];
            int count = 0;

            // Count occurrences of the current repeating character
            while (read < n && chars[read] == currentChar) {
                read++;
                count++;
            }

            // Write the character
            chars[write++] = currentChar;

            // If count > 1, write each digit of the count
            if (count > 1) {
                for (char digit : Integer.toString(count).toCharArray()) {
                    chars[write++] = digit;
                }
            }
        }

        return write;
    }

    public static void main(String[] args) {
        char[][] testCases = {
            {'a', 'a', 'b', 'b', 'c', 'c', 'c'},
            {'a'},
            {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}
        };

        for (char[] test : testCases) {
            char[] original = Arrays.copyOf(test, test.length);
            int newLength = compress(test);
            System.out.printf("Original: %s%n", Arrays.toString(original));
            System.out.printf("Compressed Length: %d, Array: %s%n%n",
                newLength, Arrays.toString(Arrays.copyOf(test, newLength)));
        }
    }
}
