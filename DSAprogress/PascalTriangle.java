import java.util.ArrayList;
import java.util.List;

/**
 * Problem Statement:
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example Input & Output:
 * Input: numRows = 5
 * Output: [
 *   [1],
 *   [1, 1],
 *   [1, 2, 1],
 *   [1, 3, 3, 1],
 *   [1, 4, 6, 4, 1]
 * ]
 *
 * Intuition & Approach:
 * The first and last element of each row in Pascal's triangle is always 1. 
 * For any intermediate element at position `j` in row `i`, its value is equal to 
 * `triangle[i-1][j-1] + triangle[i-1][j]`.
 *
 * Time Complexity: O(N^2) where N is numRows, as we visit each cell in the triangle once.
 * Space Complexity: O(N^2) to store the generated triangle rows.
 */
public class PascalTriangle {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows <= 0) {
            return triangle;
        }

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    List<Integer> prevRow = triangle.get(i - 1);
                    row.add(prevRow.get(j - 1) + prevRow.get(j));
                }
            }
            triangle.add(row);
        }

        return triangle;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Pascal's Triangle ===");
        int numRows = 5;
        List<List<Integer>> result = generate(numRows);
        System.out.println("Pascal's Triangle (5 rows):");
        for (List<Integer> row : result) {
            System.out.println(row);
        }

        System.out.println("\nPascal's Triangle (1 row):");
        List<List<Integer>> result1 = generate(1);
        for (List<Integer> row : result1) {
            System.out.println(row);
        }
    }
}
