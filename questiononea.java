public class questiononea {
    public static int minMeasurements(int k, int n) {
        // Create a DP table
        int[][] dp = new int[k + 1][n + 1];

        int m = 0; // Number of measurements

        // Loop until we find the minimum measurements required
        while (dp[k][m] < n) {
            m++;
            for (int i = 1; i <= k; i++) {
                dp[i][m] = dp[i - 1][m - 1] + dp[i][m - 1] + 1;
            }
        }

        return m; // Minimum number of measurements required
    }

    public static void main(String[] args) {
        System.out.println(minMeasurements(1, 2)); // Output: 2
        System.out.println(minMeasurements(2, 6)); // Output: 3
        System.out.println(minMeasurements(3, 14)); // Output: 4
    }
}










