import java.util.*;

/**
 * Problem Statement:
 * Given a non-negative integer N, find its factorial. The factorial of a number is the product 
 * of all positive integers less than or equal to N.
 *
 * Example Input & Output:
 * Input: N = 5
 * Output: 120
 * Explanation: 5! = 5 * 4 * 3 * 2 * 1 = 120.
 *
 * Intuition & Approach:
 * We can compute the factorial iteratively by multiplying numbers from 1 up to N. 
 * We use a `long` data type to handle moderately large results before integer overflow occurs.
 *
 * Time Complexity:
 * O(N) because we iterate N times to compute the product.
 *
 * Space Complexity:
 * O(1) as we only use a running product variable.
 */
public class FactorialNumber {

    public static long findFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void main(String[] args) {
        System.out.println("--- Running Factorial Number Tests ---");
        
        int n1 = 5;
        System.out.println("Input: " + n1);
        System.out.println("Factorial: " + findFactorial(n1));
        System.out.println();

        int n2 = 0;
        System.out.println("Input: " + n2);
        System.out.println("Factorial: " + findFactorial(n2));
        System.out.println();

        int n3 = 6;
        System.out.println("Input: " + n3);
        System.out.println("Factorial: " + findFactorial(n3));
        System.out.println();
    }
}
