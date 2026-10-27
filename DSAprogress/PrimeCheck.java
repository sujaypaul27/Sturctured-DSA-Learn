import java.util.*;

/**
 * Problem Statement:
 * Given an integer N, check whether it is prime or not. A prime number is a number greater than 1 
 * that has no positive divisors other than 1 and itself.
 *
 * Example Input & Output:
 * Input: N = 11
 * Output: true
 * Explanation: 11 is only divisible by 1 and 11, hence it is a prime number.
 *
 * Intuition & Approach:
 * Instead of checking up to N, we only need to check for factors up to the square root of N (sqrt(N)). 
 * If a number has a factor larger than its square root, it must also have a corresponding factor smaller than its square root.
 *
 * Time Complexity:
 * O(sqrt(N)) because the loop runs up to the square root of N.
 *
 * Space Complexity:
 * O(1) as no extra space is utilized.
 */
public class PrimeCheck {

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("--- Running Prime Check Tests ---");
        
        int n1 = 11;
        System.out.println("Input: " + n1);
        System.out.println("Is Prime? " + isPrime(n1));
        System.out.println();

        int n2 = 4;
        System.out.println("Input: " + n2);
        System.out.println("Is Prime? " + isPrime(n2));
        System.out.println();

        int n3 = 1;
        System.out.println("Input: " + n3);
        System.out.println("Is Prime? " + isPrime(n3));
        System.out.println();
    }
}
