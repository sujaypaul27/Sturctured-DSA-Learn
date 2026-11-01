import java.util.ArrayList;
import java.util.List;

/**
 * Problem Statement:
 * Given two sorted arrays arr1 and arr2, return a list containing their union.
 * The union of two arrays is defined as the set of distinct elements present in either array,
 * and the output must be in sorted order.
 *
 * Example 1:
 * Input: arr1 = [1, 2, 3, 4, 5], arr2 = [1, 2, 3, 6, 7]
 * Output: [1, 2, 3, 4, 5, 6, 7]
 *
 * Example 2:
 * Input: arr1 = [2, 2, 3, 4, 5], arr2 = [1, 1, 2, 5, 6]
 * Output: [1, 2, 3, 4, 5, 6]
 *
 * Intuition & Approach:
 * Since both arrays are already sorted, we can leverage the two-pointer technique:
 * - Maintain pointers `i` for arr1 and `j` for arr2.
 * - Compare arr1[i] and arr2[j].
 * - Add the smaller of the two elements to the result list, but only if it is not already
 *   equal to the last element added (to avoid duplicates).
 * - Advance the pointer corresponding to the smaller element (or both if elements are equal).
 * - Once one array is exhausted, process any remaining elements in the other array while avoiding duplicates.
 *
 * Time Complexity:
 * O(n + m) - Each element in arr1 and arr2 is processed at most once.
 *
 * Space Complexity:
 * O(n + m) - In the worst case (all elements distinct), the output list stores n + m elements.
 * Auxiliary space excluding the output list is O(1).
 */
public class UnionOfSortedArrays {

    public static List<Integer> findUnion(int[] arr1, int[] arr2) {
        List<Integer> unionList = new ArrayList<>();
        int n = arr1.length;
        int m = arr2.length;
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (arr1[i] <= arr2[j]) {
                addIfDistinct(unionList, arr1[i]);
                if (arr1[i] == arr2[j]) {
                    j++;
                }
                i++;
            } else {
                addIfDistinct(unionList, arr2[j]);
                j++;
            }
        }

        // Append remaining elements of arr1
        while (i < n) {
            addIfDistinct(unionList, arr1[i]);
            i++;
        }

        // Append remaining elements of arr2
        while (j < m) {
            addIfDistinct(unionList, arr2[j]);
            j++;
        }

        return unionList;
    }

    private static void addIfDistinct(List<Integer> list, int val) {
        if (list.isEmpty() || list.get(list.size() - 1) != val) {
            list.add(val);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Overlapping sorted arrays
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 6, 7};
        System.out.println("Test Case 1:");
        System.out.println("arr1: " + java.util.Arrays.toString(arr1));
        System.out.println("arr2: " + java.util.Arrays.toString(arr2));
        System.out.println("Union: " + findUnion(arr1, arr2));

        // Test Case 2: Arrays with duplicates
        int[] arr3 = {2, 2, 3, 4, 5};
        int[] arr4 = {1, 1, 2, 5, 6};
        System.out.println("\nTest Case 2:");
        System.out.println("arr3: " + java.util.Arrays.toString(arr3));
        System.out.println("arr4: " + java.util.Arrays.toString(arr4));
        System.out.println("Union: " + findUnion(arr3, arr4));

        // Test Case 3: Completely disjoint arrays
        int[] arr5 = {1, 3, 5};
        int[] arr6 = {2, 4, 6};
        System.out.println("\nTest Case 3:");
        System.out.println("arr5: " + java.util.Arrays.toString(arr5));
        System.out.println("arr6: " + java.util.Arrays.toString(arr6));
        System.out.println("Union: " + findUnion(arr5, arr6));
    }
}
