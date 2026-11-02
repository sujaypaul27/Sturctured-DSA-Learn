import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem Statement:
 * Given an array of integers `nums` and an integer `target`, return indices of the two numbers
 * such that they add up to `target`.
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice. You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [2, 7, 11, 15], target = 9
 * Output: [0, 1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3, 2, 4], target = 6
 * Output: [1, 2]
 *
 * Intuition & Approach:
 * A brute force search compares all pairs in O(n^2) time.
 * To optimize, we can use a Hash Map (Value -> Index).
 * For each number `nums[i]`, its complement needed to reach `target` is `complement = target - nums[i]`.
 * As we iterate through the array:
 * - If `complement` is already in the map, we have found our answer pair: return `{map.get(complement), i}`.
 * - Otherwise, store `nums[i]` and its index `i` into the map.
 * This single pass guarantees we find the pair without reusing the same element.
 *
 * Time Complexity:
 * O(n) - On average, lookup and insertion in a HashMap take O(1) time.
 *
 * Space Complexity:
 * O(n) - In the worst case, the hash map stores up to n elements.
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        // Map stores the value and its corresponding index
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }

        // Default return if no pair is found (problem statement guarantees a solution)
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[] test1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = " + Arrays.toString(test1) + ", target = " + target1);
        System.out.println("Output Indices: " + Arrays.toString(twoSum(test1, target1)));
        System.out.println();

        int[] test2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = " + Arrays.toString(test2) + ", target = " + target2);
        System.out.println("Output Indices: " + Arrays.toString(twoSum(test2, target2)));
        System.out.println();

        int[] test3 = {3, 3};
        int target3 = 6;
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = " + Arrays.toString(test3) + ", target = " + target3);
        System.out.println("Output Indices: " + Arrays.toString(twoSum(test3, target3)));
    }
}
