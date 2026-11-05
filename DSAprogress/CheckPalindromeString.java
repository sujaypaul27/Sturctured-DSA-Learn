/**
 * Problem Statement:
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example Input & Output:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Intuition & Approach:
 * Initialize two pointers, one at the beginning and one at the end of the string.
 * Move the left pointer forward if it points to a non-alphanumeric character.
 * Move the right pointer backward if it points to a non-alphanumeric character.
 * Compare the lowercase versions of characters at both pointers. If they don't match, return false.
 *
 * Time Complexity: O(N) where N is the length of the string, since we traverse the string at most once.
 * Space Complexity: O(1) as we use only pointers and constant extra memory.
 */
public class CheckPalindromeString {
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char cLeft = s.charAt(left);
            char cRight = s.charAt(right);

            if (!Character.isLetterOrDigit(cLeft)) {
                left++;
            } else if (!Character.isLetterOrDigit(cRight)) {
                right--;
            } else {
                if (Character.toLowerCase(cLeft) != Character.toLowerCase(cRight)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("--- Running CheckPalindromeString Tests ---");
        String test1 = "A man, a plan, a canal: Panama";
        System.out.println("String: \"" + test1 + "\"");
        System.out.println("Is Palindrome? " + isPalindrome(test1));

        String test2 = "race a car";
        System.out.println("String: \"" + test2 + "\"");
        System.out.println("Is Palindrome? " + isPalindrome(test2));

        String test3 = " ";
        System.out.println("String: \"" + test3 + "\"");
        System.out.println("Is Palindrome? " + isPalindrome(test3));
    }
}
