import java.util.*;

/**
 * Problem Statement:
 * Given an integer N, return all its divisors in sorted order.
 *
 * Example Input & Output:
 * Input: N = 36
 * Output: [1, 2, 3, 4, 6, 9, 12, 18, 36]
 * Explanation: All these numbers evenly divide 36.
 *
 * Intuition & Approach:
 * Divisors always appear in pairs. If 'i' divides N, then N / i also divides N. 
 * Therefore, we only need to iterate from 1 up to sqrt(N). For every 'i' that divides N, 
 * 'i' and 'N / i' are divisors. We store them in a list and sort it at the end.
 *
 * Time Complexity:
 * O(sqrt(N) + K log K) where K is the number of divisors. Iterating takes sqrt(N) time, 
 * and sorting K elements takes K log K time.
 *
 * Space Complexity:
 * O(K) to store the divisors in a list.
 */
public class PrintAllDivisors {

    public static List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        if (n <= 0) return divisors;

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }
        Collections.sort(divisors);
        return divisors;
    }

    public static void main(String[] args) {
        System.out.println("--- Running Print All Divisors Tests ---");
        
        int n1 = 36;
        System.out.println("Input: " + n1);
        System.out.println("Divisors: " + getDivisors(n1));
        System.out.println();

        int n2 = 13;
        System.out.println("Input: " + n2);
        System.out.println("Divisors: " + getDivisors(n2));
        System.out.println();
    }
}
