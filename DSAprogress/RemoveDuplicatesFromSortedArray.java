import java.util.Arrays;

/**
 * Problem Statement:
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place
 * such that each unique element appears only once. The relative order of the elements should
 * be kept the same. Return the number of unique elements k.
 * Modify nums such that the first k elements contain the unique numbers.
 *
 * Example 1:
 * Input: nums = [1, 1, 2]
 * Output: k = 2, nums = [1, 2, _]
 * Explanation: Array has 2 unique elements: 1 and 2.
 *
 * Example 2:
 * Input: nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
 * Output: k = 5, nums = [0, 1, 2, 3, 4, _, _, _, _, _]
 * Explanation: Array has 5 unique elements.
 *
 * Intuition & Approach:
 * Since the array is already sorted, duplicate values will always be adjacent to each other.
 * We can maintain two pointers:
 * - 'i': tracks the index of the last placed unique element (starts at 0).
 * - 'j': fast runner scanning through elements from index 1 to n - 1.
 * Whenever nums[j] != nums[i], it means we found a new unique element.
 * We increment 'i' and place nums[j] at nums[i].
 * Finally, the count of unique elements is i + 1.
 *
 * Time Complexity:
 * O(N) where N is the length of nums, because the fast pointer visits each element once.
 *
 * Space Complexity:
 * O(1) auxiliary space as the modification is done in-place without extra collections.
 */
public class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0; // Index of the last confirmed unique element

        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 1, 2};
        System.out.println("Original array: " + Arrays.toString(test1));
        int k1 = removeDuplicates(test1);
        System.out.print("Unique count: " + k1 + ", Modified prefix: [");
        for (int idx = 0; idx < k1; idx++) {
            System.out.print(test1[idx] + (idx < k1 - 1 ? ", " : ""));
        }
        System.out.println("]\n");

        int[] test2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("Original array: " + Arrays.toString(test2));
        int k2 = removeDuplicates(test2);
        System.out.print("Unique count: " + k2 + ", Modified prefix: [");
        for (int idx = 0; idx < k2; idx++) {
            System.out.print(test2[idx] + (idx < k2 - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}
