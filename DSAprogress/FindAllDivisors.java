import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindAllDivisors {
    public List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        if (n <= 0) {
            return divisors;
        }

        for (int i = 1; (long) i * i <= n; i++) {
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
}
