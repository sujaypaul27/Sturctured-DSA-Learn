import java.util.Arrays;

/**
 * Problem Statement:
 * A peak element in an array is an element that is strictly greater than its neighbors.
 * Given an integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always
 * considered to be strictly greater than a neighbor that is outside the array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Examples:
 * Input: nums = [1, 2, 3, 1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return index 2.
 *
 * Input: nums = [1, 2, 1, 3, 5, 6, 4]
 * Output: 5 (or index 1)
 * Explanation: Index 5 (element 6) or index 1 (element 2) are both valid peaks.
 *
 * Intuition & Approach:
 * A linear scan can easily find a peak in O(N) time, but we need O(log N).
 * We can apply binary search based on local gradients:
 * - Calculate mid = low + (high - low) / 2.
 * - If nums[mid] < nums[mid + 1], then a peak MUST exist on the right side of mid
 *   (because either the sequence keeps increasing till the end, making the last element a peak,
 *   or it drops somewhere, forming a peak along the way).
 * - Otherwise, nums[mid] > nums[mid + 1], which means mid could be the peak, or a peak exists
 *   to its left.
 * - We adjust low and high accordingly until low == high.
 *
 * Complexity Analysis:
 * - Time Complexity: O(log N), because the search interval is halved at each iteration.
 * - Space Complexity: O(1), as only a few pointers are used.
 */
public class FindPeakElement {

    /**
     * Finds any peak element's index using binary search.
     *
     * @param nums input array of integers
     * @return index of a peak element
     */
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If the slope is upward, peak must lie to the right of mid
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                // If the slope is downward, mid itself could be the peak or peak lies to left
                high = mid;
            }
        }

        // low and high converge to the peak index
        return low;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 1};
        int peakIndex1 = findPeakElement(test1);
        System.out.println("Input: " + Arrays.toString(test1));
        System.out.println("Peak Element: " + test1[peakIndex1] + " at index: " + peakIndex1);
        System.out.println();

        int[] test2 = {1, 2, 1, 3, 5, 6, 4};
        int peakIndex2 = findPeakElement(test2);
        System.out.println("Input: " + Arrays.toString(test2));
        System.out.println("Peak Element: " + test2[peakIndex2] + " at index: " + peakIndex2);
        System.out.println();

        int[] test3 = {10, 20, 15, 2, 23, 90, 67};
        int peakIndex3 = findPeakElement(test3);
        System.out.println("Input: " + Arrays.toString(test3));
        System.out.println("Peak Element: " + test3[peakIndex3] + " at index: " + peakIndex3);
    }
}
