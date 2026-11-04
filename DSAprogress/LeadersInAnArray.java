import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem Statement:
 * Given an array of integers, print all the leaders in the array. 
 * An element is a leader if it is greater than all the elements to its right side.
 * The rightmost element is always a leader.
 *
 * Example Input & Output:
 * Input: arr = [10, 22, 12, 3, 0, 6]
 * Output: [22, 12, 6]
 * Explanation: 6 is a leader (no right elements). 3 is not. 0 is not. 12 is greater than 3, 0, 6. 
 * 22 is greater than 12, 3, 0, 6. 10 is not greater than 22.
 *
 * Intuition & Approach:
 * Instead of checking the right of every element in O(N^2) time, we can iterate from right to left.
 * Keep track of the maximum element seen so far from the right. If the current element is 
 * greater than the max_so_far, it is a leader, and we update max_so_far.
 *
 * Time Complexity: O(N) because we traverse the array once from right to left.
 * Space Complexity: O(N) in the worst case to store the result list of leaders.
 */
public class LeadersInAnArray {

    public static List<Integer> findLeaders(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return leaders;
        }

        int n = arr.length;
        int maxRight = arr[n - 1];
        // The rightmost element is always a leader
        leaders.add(maxRight);

        // Traverse from right to second element
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > maxRight) {
                maxRight = arr[i];
                leaders.add(maxRight);
            }
        }

        // Since we collected from right to left, reverse to maintain original relative order
        Collections.reverse(leaders);
        return leaders;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Leaders in an Array ===");
        int[] arr1 = {10, 22, 12, 3, 0, 6};
        System.out.println("Input: [10, 22, 12, 3, 0, 6]");
        System.out.println("Leaders: " + findLeaders(arr1));

        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("\nInput: [1, 2, 3, 4, 5]");
        System.out.println("Leaders: " + findLeaders(arr2));

        int[] arr3 = {5, 4, 3, 2, 1};
        System.out.println("\nInput: [5, 4, 3, 2, 1]");
        System.out.println("Leaders: " + findLeaders(arr3));
    }
}
