import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array `nums` with `n` objects colored red, white, or blue, sort them in-place so
 * that objects of the same color are adjacent, with the colors in the order red (0), white (1), and blue (2).
 * We must solve this problem without using the library's sort function, and in one pass using constant extra space.
 *
 * Example 1:
 * Input: nums = [2, 0, 2, 1, 1, 0]
 * Output: [0, 0, 1, 1, 2, 2]
 *
 * Example 2:
 * Input: nums = [2, 0, 1]
 * Output: [0, 1, 2]
 *
 * Intuition & Approach:
 * This is the classic Dutch National Flag problem proposed by Edsger W. Dijkstra.
 * We partition the array into 4 regions using 3 pointers:
 * - `low`: Boundary for 0s. All elements before index `low` are 0s.
 * - `mid`: Current element under inspection. Elements from `low` to `mid - 1` are 1s.
 * - `high`: Boundary for 2s. All elements after index `high` are 2s.
 * - Unknown region: `mid` to `high`.
 *
 * While `mid <= high`:
 * 1. If nums[mid] == 0: swap nums[low] and nums[mid], then low++, mid++.
 * 2. If nums[mid] == 1: it's in the right section, just mid++.
 * 3. If nums[mid] == 2: swap nums[mid] and nums[high], then high--.
 *    (Note: mid is not incremented here because the swapped element from high has not yet been processed).
 *
 * Time Complexity:
 * O(n) - Single pass where either mid increases or high decreases at every step.
 *
 * Space Complexity:
 * O(1) - Sorting is done in-place with no extra data structures.
 */
public class SortColorsDutchNationalFlag {

    public static void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 0, 2, 1, 1, 0};
        System.out.println("Test Case 1:");
        System.out.println("Before: " + Arrays.toString(test1));
        sortColors(test1);
        System.out.println("After:  " + Arrays.toString(test1));
        System.out.println();

        int[] test2 = {2, 0, 1};
        System.out.println("Test Case 2:");
        System.out.println("Before: " + Arrays.toString(test2));
        sortColors(test2);
        System.out.println("After:  " + Arrays.toString(test2));
        System.out.println();

        int[] test3 = {1, 2, 0, 1, 2, 0, 0, 1};
        System.out.println("Test Case 3:");
        System.out.println("Before: " + Arrays.toString(test3));
        sortColors(test3);
        System.out.println("After:  " + Arrays.toString(test3));
    }
}
