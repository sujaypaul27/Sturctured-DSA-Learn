import java.util.Arrays;

/**
 * Problem Statement:
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's. 
 * Do it in-place.
 *
 * Example Input & Output:
 * Input: matrix = [
 *   [1, 1, 1],
 *   [1, 0, 1],
 *   [1, 1, 1]
 * ]
 * Output: [
 *   [1, 0, 1],
 *   [0, 0, 0],
 *   [1, 0, 1]
 * ]
 *
 * Intuition & Approach:
 * Instead of using extra arrays for row and column markers, we can use the first row and 
 * first column of the matrix itself to act as markers. A separate variable is kept to track 
 * if the first column itself needs to be zeroed out.
 *
 * Time Complexity: O(M * N) to traverse and update the matrix.
 * Space Complexity: O(1) as we use existing matrix cells for marking.
 */
public class SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean col0 = false;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
            }
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Traverse from bottom-up to avoid overwriting markers prematurely
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Set Matrix Zeroes ===");
        int[][] matrix1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        System.out.println("Original Matrix:");
        printMatrix(matrix1);
        setZeroes(matrix1);
        System.out.println("Modified Matrix:");
        printMatrix(matrix1);

        int[][] matrix2 = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };
        System.out.println("\nOriginal Matrix 2:");
        printMatrix(matrix2);
        setZeroes(matrix2);
        System.out.println("Modified Matrix 2:");
        printMatrix(matrix2);
    }
}
