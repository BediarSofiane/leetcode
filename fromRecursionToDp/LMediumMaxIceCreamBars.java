package fromRecursionToDp;

import java.util.HashMap;
import java.util.Map;

public class LMediumMaxIceCreamBars {
    public int maxIceCream(int[] costs, int coins) {
        Integer[][] memo = new Integer[costs.length][coins+1];
        return recurse(costs, coins, costs.length - 1, memo);
    }

    private int recurse(int[] costs, int remainingCoins, int index, Integer[][] memo) {
        if (index < 0) {
            return 0;
        }
        if (memo[index][remainingCoins] != null) {
            return memo[index][remainingCoins];
        }
        if (costs[index] > remainingCoins) {
            memo[index][remainingCoins] = recurse(costs, remainingCoins, index - 1, memo);
        } else {
            memo[index][remainingCoins] = Math.max(
                    recurse(costs, remainingCoins, index - 1, memo),
                    recurse(costs, remainingCoins - costs[index], index - 1, memo) + 1);
        }
        return memo[index][remainingCoins];
    }
    
    private int dp(int[] costs, int coins) {
        int[][] dp = new int[costs.length][coins + 1];
        for (int i = 0; i < costs.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < coins + 1; j++) {
            dp[0][j] = costs[0] > j ? 0 : costs[0];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (costs[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costs[i]] + 1);
                }
            }
        }
        return dp[costs.length - 1][coins];
    }
    
    private int dpWithSpaceOptimization(int[] costs, int coins) {
        int[] previousDp = new int[coins + 1];
        int[] currentDp = new int[coins + 1];
        previousDp[0] = 0;
        currentDp[0] = 0;
        for (int j = 1; j < coins + 1; j++) {
            previousDp[j] = costs[0] > j ? 0 : costs[0];
        }
        
        for (int i = 1; i < costs.length; i++) {
            for (int j = 1; j < coins + 1; j++) {
                if (costs[i] > j) {
                    currentDp[j] = previousDp[j];
                } else {
                    currentDp[j] = Math.max(previousDp[j], previousDp[j - costs[i]] + 1);
                }
            }
            previousDp = currentDp;
            currentDp = new int[coins + 1];
            currentDp[0] = 0;
        }
        return previousDp[coins];
    }
}
