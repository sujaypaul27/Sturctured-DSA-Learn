import java.util.Arrays;

/**
 * Problem Statement:
 * Given a non-empty array of integers `nums`, every element appears twice except for one.
 * Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 * Input: nums = [2, 2, 1]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [4, 1, 2, 1, 2]
 * Output: 4
 *
 * Intuition & Approach:
 * A hash table could store frequencies, but that requires O(n) auxiliary space.
 * To achieve O(1) space, we exploit the properties of bitwise XOR (^):
 * 1. a ^ a = 0 (Any number XORed with itself results in 0)
 * 2. a ^ 0 = a (Any number XORed with 0 remains unchanged)
 * 3. XOR is associative and commutative: order of operations does not matter.
 *
 * If we XOR all elements together, all pairs will cancel each other out to 0,
 * leaving behind only the unique number.
 *
 * Time Complexity:
 * O(n) - Single traversal through the entire array.
 *
 * Space Complexity:
 * O(1) - Only one integer accumulator variable is used.
 */
public class FindSingleNumber {

    public static int singleNumber(int[] nums) {
        int unique = 0;
        for (int num : nums) {
            unique ^= num;
        }
        return unique;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 2, 1};
        int[] test2 = {4, 1, 2, 1, 2};
        int[] test3 = {1};

        System.out.println("Test Case 1: " + Arrays.toString(test1));
        System.out.println("Single Element: " + singleNumber(test1));
        System.out.println();

        System.out.println("Test Case 2: " + Arrays.toString(test2));
        System.out.println("Single Element: " + singleNumber(test2));
        System.out.println();

        System.out.println("Test Case 3: " + Arrays.toString(test3));
        System.out.println("Single Element: " + singleNumber(test3));
    }
}
