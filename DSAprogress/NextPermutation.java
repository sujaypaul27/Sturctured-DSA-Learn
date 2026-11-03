import java.util.*;

/**
 * Problem Statement:
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such an arrangement is not possible, it must be rearranged as the lowest possible order (sorted in ascending order).
 *
 * Example Input & Output:
 * Input: nums = [1, 2, 3]
 * Output: [1, 3, 2]
 *
 * Intuition & Approach:
 * 1. Find the first index `ind` from the back such that nums[ind] < nums[ind + 1].
 * 2. If no such index exists, the array is in descending order; reverse it to get the lowest permutation.
 * 3. Otherwise, find the smallest number greater than nums[ind] from the back and swap them.
 * 4. Reverse the subarray from ind + 1 to the end to get the smallest lexicographical arrangement.
 *
 * Time Complexity: O(3N) which simplifies to O(N).
 * Space Complexity: O(1) in-place modification.
 */
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int ind = -1;
        
        // Step 1: Find the break point
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind = i;
                break;
            }
        }
        
        // If break point does not exist, reverse the whole array
        if (ind == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        
        // Step 2: Find the next greater element and swap
        for (int i = n - 1; i > ind; i--) {
            if (nums[i] > nums[ind]) {
                int temp = nums[i];
                nums[i] = nums[ind];
                nums[ind] = temp;
                break;
            }
        }
        
        // Step 3: Reverse the remaining part
        reverse(nums, ind + 1, n - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running NextPermutation Tests ---");
        int[] test1 = {1, 2, 3};
        nextPermutation(test1);
        System.out.println("Input [1, 2, 3] Next Permutation: " + Arrays.toString(test1));

        int[] test2 = {3, 2, 1};
        nextPermutation(test2);
        System.out.println("Input [3, 2, 1] Next Permutation: " + Arrays.toString(test2));

        int[] test3 = {1, 1, 5};
        nextPermutation(test3);
        System.out.println("Input [1, 1, 5] Next Permutation: " + Arrays.toString(test3));
    }
}
