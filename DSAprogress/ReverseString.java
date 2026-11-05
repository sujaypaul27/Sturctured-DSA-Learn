import java.util.Arrays;

/**
 * Problem Statement:
 * Write a function that reverses a string. The input string is given as an array of characters s.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example Input & Output:
 * Input: s = ['h','e','l','l','o']
 * Output: ['o','l','l','e','h']
 * Explanation: The characters of the string are mirrored using two pointers.
 *
 * Intuition & Approach:
 * Use two pointers, left starting at index 0 and right starting at s.length - 1.
 * Swap the characters at left and right pointers, then move left forward and right backward
 * until they meet in the middle.
 *
 * Time Complexity: O(N) where N is the length of the string, as we visit each character once.
 * Space Complexity: O(1) as we only use a few variables for pointers and perform in-place swaps.
 */
public class ReverseString {
    public static void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running ReverseString Tests ---");
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        System.out.println("Before: " + Arrays.toString(s1));
        reverseString(s1);
        System.out.println("After:  " + Arrays.toString(s1));

        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        System.out.println("Before: " + Arrays.toString(s2));
        reverseString(s2);
        System.out.println("After:  " + Arrays.toString(s2));
    }
}
