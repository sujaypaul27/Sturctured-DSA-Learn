import java.util.*;

public class PowerOfANumber {
    public static double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double currentProduct = x;
        for (long i = N; i > 0; i /= 2) {
            if (i % 2 == 1) {
                ans = ans * currentProduct;
            }
            currentProduct = currentProduct * currentProduct;
        }
        return ans;
    }
}
