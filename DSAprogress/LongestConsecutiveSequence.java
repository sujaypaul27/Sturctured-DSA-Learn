import java.util.HashSet;
import java.util.Set;

/**
 * Problem Statement:
 * Given an unsorted array of integers nums, return the length of the longest 
 * consecutive elements sequence. You must write an algorithm that runs in O(n) time.
 *
 * Example Input & Output:
 * Input: nums = [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Intuition & Approach:
 * To achieve O(N) time, sorting is ruled out. We can insert all elements into a HashSet. 
 * For each number, we check if it is the start of a sequence by checking if `num - 1` exists in the set. 
 * If `num - 1` does NOT exist, it's the start of a sequence, and we can count consecutive numbers 
 * (`num + 1`, `num + 2`, ...) until they are no longer in the set.
 *
 * Time Complexity: O(N) because each number is processed a constant number of times.
 * Space Complexity: O(N) to store elements in the HashSet.
 */
public class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            // Check if this is the start of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Count consecutive numbers
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Longest Consecutive Sequence ===");
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Input: [100, 4, 200, 1, 3, 2]");
        System.out.println("Longest Consecutive Length: " + longestConsecutive(nums1));

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("\nInput: [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]");
        System.out.println("Longest Consecutive Length: " + longestConsecutive(nums2));

        int[] nums3 = {};
        System.out.println("\nInput: []");
        System.out.println("Longest Consecutive Length: " + longestConsecutive(nums3));
    }
}
