import java.util.*;

/**
 * Problem Statement:
 * Given two numbers, base 'x' and exponent 'n', calculate x^n using recursion.
 * Handle both positive and negative powers (binary exponentiation approach).
 *
 * Example 1:
 * Input: x = 2.0, n = 10
 * Output: 1024.0
 * Explanation: 2^10 = 1024.
 *
 * Example 2:
 * Input: x = 2.0, n = -2
 * Output: 0.25
 * Explanation: 2^(-2) = 1 / (2^2) = 1 / 4 = 0.25.
 *
 * Intuition & Approach:
 * A naive recursion does x * power(x, n - 1), which takes O(n) calls.
 * Instead, we use Divide & Conquer (Binary Exponentiation):
 * - If n is even, x^n = (x^(n/2))^2.
 * - If n is odd, x^n = x * (x^(n/2))^2.
 * For negative n, we compute 1 / power(x, -n). To prevent integer overflow
 * when n = Integer.MIN_VALUE, we cast n to long before inverting.
 *
 * Time Complexity:
 * O(log n) because the exponent is halved in each recursive call.
 *
 * Space Complexity:
 * O(log n) due to the recursive call stack.
 */
public class PowerOfNumberRecursion {

    public static double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            x = 1.0 / x;
            exp = -exp;
        }
        return powerHelper(x, exp);
    }

    private static double powerHelper(double x, long n) {
        if (n == 0) {
            return 1.0;
        }

        double half = powerHelper(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Positive power
        double x1 = 2.0;
        int n1 = 10;
        System.out.println("Test Case 1:");
        System.out.println("Input: base = " + x1 + ", exponent = " + n1);
        System.out.println("Output: " + myPow(x1, n1));
        System.out.println();

        // Test Case 2: Negative power
        double x2 = 2.0;
        int n2 = -3;
        System.out.println("Test Case 2:");
        System.out.println("Input: base = " + x2 + ", exponent = " + n2);
        System.out.println("Output: " + myPow(x2, n2));
        System.out.println();

        // Test Case 3: Power 0 and odd positive power
        double x3 = 3.0;
        int n3 = 5;
        System.out.println("Test Case 3:");
        System.out.println("Input: base = " + x3 + ", exponent = " + n3);
        System.out.println("Output: " + myPow(x3, n3));
    }
}
