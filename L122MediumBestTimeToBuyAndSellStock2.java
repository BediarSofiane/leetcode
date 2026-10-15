class L122MediumBestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        // return maxProfitTopDown(prices);
        // return maxProfitBottomUp(prices);
        return maxProfitBottomUpSpaceOptimized(prices);
    }

    private int maxProfitBottomUpSpaceOptimized(int[] prices) {
        int n = prices.length;
        int daysResultWithStock = prices[n - 1];
        int daysResultWithoutStock = 0;
        for (int i = n - 2; i >= 0; i--) {
            int tmp = daysResultWithoutStock;
            daysResultWithoutStock = Math.max(daysResultWithoutStock, daysResultWithStock - prices[i]);
            daysResultWithStock = Math.max(daysResultWithStock, tmp + prices[i]);
        }
        return daysResultWithoutStock;

    }

    private int maxProfitBottomUp(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2]; // 0 is false and 1 is true for hasStock boolean value
        dp[n - 1][0] = 0;
        dp[n - 1][1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i + 1][1], dp[i + 1][0] + prices[i]);
        }
        return dp[0][0];

    }

    private int maxProfitTopDown(int[] prices) {
        Map<Context, Integer> memo = new HashMap<>();
        return Math.max(0, recurseWithMemo(prices, 0, false, memo));
    }

    // returns: max profit achievable from day `index` onward, given hasStock
    // status.
    // assumes: recursive calls on index+1 correctly compute max profit for their
    // state.
    // base case: index == n-1 → prices[n-1] if hasStock (sell), else 0 (nothing to
    // do).
    // transition: if hasStock → max(sell today + recurse(index+1, false), hold →
    // recurse(index+1, true))
    // if !hasStock → max(buy today + recurse(index+1, true), skip →
    // recurse(index+1, false))
    // derived by: state-machine — per-day decision set depends on hasStock mode.
    // Augmented state with hasStock to make the per-step menu uniform conditional
    // on state.
    private int recurse(int[] prices, int index, boolean hasStock) {
        if (index == prices.length - 1) {
            if (hasStock) {
                return prices[index];
            }
            return 0;
        }
        if (hasStock) {
            return Math.max(recurse(prices, index + 1, false) + prices[index], recurse(prices, index + 1, hasStock));
        } else {
            return Math.max(recurse(prices, index + 1, true) - prices[index], recurse(prices, index + 1, hasStock));
        }

    }

    private int recurseWithMemo(int[] prices, int index, boolean hasStock, Map<Context, Integer> memo) {
        Context context = new Context(index, hasStock);
        Integer fromMemo = memo.get(context);
        if (fromMemo != null) {
            return fromMemo;
        }
        int result;
        if (index == prices.length - 1) {
            if (hasStock) {
                result = prices[index];
            } else {
                result = 0;
            }
        } else {
            if (hasStock) {
                result = Math.max(recurseWithMemo(prices, index + 1, false, memo) + prices[index],
                        recurseWithMemo(prices, index + 1, hasStock, memo));
            } else {
                result = Math.max(recurseWithMemo(prices, index + 1, true, memo) - prices[index],
                        recurseWithMemo(prices, index + 1, hasStock, memo));
            }
        }

        memo.put(context, result);
        return result;
    }

    private record Context(int index, boolean bool) {
    };
}