public class FrogJump {
    public static int minCost(int[] heights) {
        int n = heights.length;
        if (n <= 1) return 0;
        int prev2 = 0;
        int prev1 = Math.abs(heights[1] - heights[0]);
        if (n == 2) return prev1;
        int current = 0;
        for (int i = 2; i < n; i++) {
            int oneStep = prev1 + Math.abs(heights[i] - heights[i - 1]);
            int twoStep = prev2 + Math.abs(heights[i] - heights[i - 2]);
            current = Math.min(oneStep, twoStep);
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
}
