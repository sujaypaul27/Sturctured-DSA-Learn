import java.util.*;

/**
 * Problem Statement:
 * Given a non-negative integer x, compute and return the square root of x.
 * Since the return type is an integer, the decimal digits are truncated,
 * and only the integer part of the result is returned.
 * Note: You must not use any built-in exponent function or operator (like Math.sqrt or x ** 0.5).
 *
 * Examples:
 * Input: x = 4  -> Output: 2
 * Input: x = 8  -> Output: 2 (The square root of 8 is ~2.82842..., floor is 2)
 * Input: x = 0  -> Output: 0
 *
 * Intuition & Approach:
 * The square root of x lies in the range [0, x]. For x >= 1, it lies in [1, x].
 * Because the search space is monotonically increasing, we can use Binary Search.
 * At each step, take `mid = low + (high - low) / 2`.
 * To prevent integer overflow when computing `mid * mid`, we compare `mid` with `x / mid`.
 * - If `mid <= x / mid`, then `mid` could be a valid candidate; we store it and search the right half.
 * - If `mid > x / mid`, `mid` is too large; we search the left half.
 *
 * Time Complexity: O(log x) - Search space is halved in every iteration.
 * Space Complexity: O(1) - Constant auxiliary space.
 */
public class SquareRootInteger {

    /**
     * Returns the floor of the square root of x using binary search.
     *
     * @param x non-negative integer
     * @return floor value of square root of x
     */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        int low = 1;
        int high = x;
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Compare mid with x / mid to prevent integer overflow
            if (mid <= x / mid) {
                ans = mid;
                low = mid + 1; // Try to find a larger value
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] testCases = {0, 1, 4, 8, 15, 16, 2147395599};

        System.out.println("=== Testing Integer Square Root ===");
        for (int x : testCases) {
            int result = mySqrt(x);
            System.out.printf("sqrt(%d) = %d%n", x, result);
        }
    }
}
