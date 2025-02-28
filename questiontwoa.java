public class questiontwoa {
    public static int minRewards(int[] ratings) {
        int n = ratings.length;
        int[] rewards = new int[n];

        // Step 1: Initialize all rewards to 1
        for (int i = 0; i < n; i++) {
            rewards[i] = 1;
        }

        // Step 2: First pass (left to right)
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }

        // Step 3: Second pass (right to left)
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }

        // Step 4: Sum up the rewards
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward;
        }

        return totalRewards;
    }

    public static void main(String[] args) {
        int[] ratings1 = {1, 0, 2};
        System.out.println(minRewards(ratings1)); // Output: 5

        int[] ratings2 = {1, 2, 2};
        System.out.println(minRewards(ratings2)); // Output: 4
    }
}




