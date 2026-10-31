import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array of integers, find the second largest and second smallest elements
 * in the array without sorting the array. If no such element exists (e.g. all elements are identical),
 * return -1 for that position.
 *
 * Example 1:
 * Input: arr = [1, 2, 4, 7, 7, 5]
 * Output: Second Smallest = 2, Second Largest = 5
 * Explanation: Smallest is 1, second smallest is 2. Largest is 7, second largest distinct element is 5.
 *
 * Example 2:
 * Input: arr = [10, 10, 10]
 * Output: Second Smallest = -1, Second Largest = -1
 * Explanation: All elements are identical, so no distinct second smallest or largest exists.
 *
 * Intuition & Approach:
 * A naive approach is sorting (O(N log N)), but we can find both values in a single pass O(N).
 * We maintain two variables for largest and second largest:
 * - 'largest' initialized to Integer.MIN_VALUE, 'secondLargest' to Integer.MIN_VALUE.
 * - When iterating: if arr[i] > largest, secondLargest becomes largest, and largest becomes arr[i].
 * - Else if arr[i] > secondLargest and arr[i] != largest, secondLargest becomes arr[i].
 * We apply the symmetric logic to find the smallest and second smallest.
 *
 * Time Complexity:
 * O(N) where N is the length of the array, as we scan the array in a single traversal.
 *
 * Space Complexity:
 * O(1) auxiliary space since we only store a few scalar variables.
 */
public class SecondLargestElement {

    public static int findSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return (secondLargest == Integer.MIN_VALUE) ? -1 : secondLargest;
    }

    public static int findSecondSmallest(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest && num != smallest) {
                secondSmallest = num;
            }
        }

        return (secondSmallest == Integer.MAX_VALUE) ? -1 : secondSmallest;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 4, 7, 7, 5};
        System.out.println("Array: " + Arrays.toString(test1));
        System.out.println("Second Smallest: " + findSecondSmallest(test1));
        System.out.println("Second Largest: " + findSecondLargest(test1));
        System.out.println();

        int[] test2 = {10, 10, 10};
        System.out.println("Array: " + Arrays.toString(test2));
        System.out.println("Second Smallest: " + findSecondSmallest(test2));
        System.out.println("Second Largest: " + findSecondLargest(test2));
        System.out.println();

        int[] test3 = {8, 1, 9, 3, 7};
        System.out.println("Array: " + Arrays.toString(test3));
        System.out.println("Second Smallest: " + findSecondSmallest(test3));
        System.out.println("Second Largest: " + findSecondLargest(test3));
    }
}
