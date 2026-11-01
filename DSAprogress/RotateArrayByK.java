import java.util.Arrays;

/**
 * Problem Statement:
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 * Input: nums = [1, 2, 3, 4, 5, 6, 7], k = 3
 * Output: [5, 6, 7, 1, 2, 3, 4]
 * Explanation: 
 * Rotate 1 step right: [7, 1, 2, 3, 4, 5, 6]
 * Rotate 2 steps right: [6, 7, 1, 2, 3, 4, 5]
 * Rotate 3 steps right: [5, 6, 7, 1, 2, 3, 4]
 *
 * Intuition & Approach:
 * Rotating by k positions can be achieved in-place using the Reversal Algorithm:
 * 1. Normalize k by taking k = k % n (rotating n times results in the same array).
 * 2. Reverse the entire array.
 * 3. Reverse the first k elements (indices 0 to k - 1).
 * 4. Reverse the remaining n - k elements (indices k to n - 1).
 * This achieves the right shift without requiring any auxiliary array.
 *
 * Time Complexity:
 * O(n) - We reverse the array three times; total elements visited is proportional to 2 * n.
 *
 * Space Complexity:
 * O(1) - The rotation is performed in-place using constant auxiliary space.
 */
public class RotateArrayByK {

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        k = k % n;
        if (k == 0) {
            return;
        }

        // Step 1: Reverse entire array
        reverse(nums, 0, n - 1);
        // Step 2: Reverse first k elements
        reverse(nums, 0, k - 1);
        // Step 3: Reverse remaining n - k elements
        reverse(nums, k, n - 1);
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
        // Test Case 1
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        System.out.println("Test Case 1:");
        System.out.println("Original: " + Arrays.toString(arr1) + ", k = " + k1);
        rotate(arr1, k1);
        System.out.println("Rotated:  " + Arrays.toString(arr1));

        // Test Case 2: k is larger than array length
        int[] arr2 = {-1, -100, 3, 99};
        int k2 = 6; // 6 % 4 = 2
        System.out.println("\nTest Case 2:");
        System.out.println("Original: " + Arrays.toString(arr2) + ", k = " + k2);
        rotate(arr2, k2);
        System.out.println("Rotated:  " + Arrays.toString(arr2));

        // Test Case 3: k = 0 or k % n == 0
        int[] arr3 = {10, 20, 30};
        int k3 = 3;
        System.out.println("\nTest Case 3:");
        System.out.println("Original: " + Arrays.toString(arr3) + ", k = " + k3);
        rotate(arr3, k3);
        System.out.println("Rotated:  " + Arrays.toString(arr3));
    }
}
