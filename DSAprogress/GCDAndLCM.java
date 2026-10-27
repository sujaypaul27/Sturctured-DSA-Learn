import java.util.*;

/**
 * Problem Statement:
 * Given two integers A and B, find their Greatest Common Divisor (GCD) and Least Common Multiple (LCM).
 *
 * Example Input & Output:
 * Input: A = 20, B = 15
 * Output: GCD = 5, LCM = 60
 * Explanation: Factors of 20 are 1,2,4,5,10,20. Factors of 15 are 1,3,5,15. The greatest common factor is 5.
 * LCM is given by (A * B) / GCD(A, B) = (20 * 15) / 5 = 60.
 *
 * Intuition & Approach:
 * We use the Euclidean algorithm to find the GCD efficiently. The algorithm states that GCD(A, B) is the same as GCD(B, A % B) 
 * until B becomes 0. Once B is 0, A is the GCD. For LCM, we use the mathematical property: A * B = GCD(A, B) * LCM(A, B).
 *
 * Time Complexity:
 * O(log(min(A, B))) because the Euclidean algorithm reduces the numbers logarithmically with the modulo operation.
 *
 * Space Complexity:
 * O(1) as we only use a few variables for computation.
 */
public class GCDAndLCM {

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long lcm(long a, long b, long gcdVal) {
        if (gcdVal == 0) return 0;
        return (a / gcdVal) * b;
    }

    public static void main(String[] args) {
        System.out.println("--- Running GCD and LCM Tests ---");
        
        long a1 = 20, b1 = 15;
        long g1 = gcd(a1, b1);
        long l1 = lcm(a1, b1, g1);
        System.out.println("Input: A = " + a1 + ", B = " + b1);
        System.out.println("Result -> GCD: " + g1 + ", LCM: " + l1);
        System.out.println();

        long a2 = 47, b2 = 91;
        long g2 = gcd(a2, b2);
        long l2 = lcm(a2, b2, g2);
        System.out.println("Input: A = " + a2 + ", B = " + b2);
        System.out.println("Result -> GCD: " + g2 + ", LCM: " + l2);
        System.out.println();
    }
}
