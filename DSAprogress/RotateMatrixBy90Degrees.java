import java.util.Arrays;

/**
 * Problem Statement:
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place.
 *
 * Example Input & Output:
 * Input: matrix = [
 *   [1, 2, 3],
 *   [4, 5, 6],
 *   [7, 8, 9]
 * ]
 * Output: [
 *   [7, 4, 1],
 *   [8, 5, 2],
 *   [9, 6, 3]
 * ]
 *
 * Intuition & Approach:
 * Rotating a matrix 90 degrees clockwise is equivalent to:
 * 1. Transposing the matrix (swapping matrix[i][j] with matrix[j][i]).
 * 2. Reversing each row of the transposed matrix.
 *
 * Time Complexity: O(N^2) for transpose and row reversals.
 * Space Complexity: O(1) since the rotation is done in-place.
 */
public class RotateMatrixBy90Degrees {

    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Rotate Matrix by 90 Degrees ===");
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Original Matrix:");
        printMatrix(matrix1);
        rotate(matrix1);
        System.out.println("Rotated Matrix:");
        printMatrix(matrix1);

        int[][] matrix2 = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        System.out.println("\nOriginal Matrix 2:");
        printMatrix(matrix2);
        rotate(matrix2);
        System.out.println("Rotated Matrix 2:");
        printMatrix(matrix2);
    }
}
