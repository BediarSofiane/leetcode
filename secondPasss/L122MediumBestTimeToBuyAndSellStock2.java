class Solution {
    public int maxProfit(int[] prices) {
        //return maxProfitBottomUp(prices);
        //return maxProfitBottomUpOptimized(prices);
        //return maxProfitTopDown(prices);
        //return maxProfitTopDownMemo(prices);
        return maxProfitBackTracking(prices);
    }

    private int maxProfitBottomUp(int[] prices){
        int n = prices.length;
        int result =0;
        int[][] dp = new int[n][2]; //0 is for when hasStock is false and 1 is for hasStock being true.
        dp[n-1][0] = 0;
        dp[n-1][1] = prices[n-1];
        for(int i=n-2; i>=0; i--){
            dp[i][0] = Math.max(dp[i+1][0], dp[i+1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i+1][1], dp[i+1][0] + prices[i]);
        }
        return dp[0][0];
    }

    private int maxProfitBottomUpOptimized(int[] prices){
        int n = prices.length;
        int result =0;
        int profitWithStock = prices[n-1];
        int profitWithoutStock = 0;

        for(int i=n-2; i>=0; i--){
            int memo = profitWithoutStock;
            profitWithoutStock = Math.max(profitWithoutStock, profitWithStock - prices[i]);
            profitWithStock = Math.max(profitWithStock, memo + prices[i]);
        }
        return profitWithoutStock;
    }

    private int maxProfitTopDown(int[] prices){
        return sol(prices,0, false);
    }

    private int sol(int[] prices, int i, boolean hasStock){
        if(i== prices.length){
            return 0;
        }
        if(hasStock){
            return Math.max(sol(prices, i+1, true), sol(prices, i+1, false) + prices[i]);
        } else{
            return Math.max(sol(prices, i+1, false), sol(prices, i+1, true) - prices[i]);
        }
    }

    private int maxProfitTopDownMemo(int[] prices){
        return solMemo(prices, 0, false, new HashMap<>());
    }

    private int solMemo(int[] prices, int i, boolean hasStock, Map<Context, Integer> memo){
        if(i== prices.length){
            return 0;
        }
        Context context = new Context(i, hasStock);
        if(memo.containsKey(context)){
            return memo.get(context);
        }
        int result = 0;
        if(hasStock){
            result = Math.max(solMemo(prices, i+1, true, memo), solMemo(prices, i+1, false, memo) + prices[i]);
        } else{
            result = Math.max(solMemo(prices, i+1, false, memo), solMemo(prices, i+1, true, memo) - prices[i]);
        }
        memo.put(context, result);
        return result;
    }

    private int maxProfitBackTracking(int[] prices){
        return backTrack(prices, 0, false, 0);

    }

    private int backTrack(int[] prices, int i, boolean hasStock, int profit){
        if(i == prices.length){
            return profit;
        }

        if(hasStock){
            return Math.max(backTrack(prices, i+1, true, profit), backTrack(prices, i+1, false, profit + prices[i]));
        } else {
            return Math.max(backTrack(prices, i+1, false, profit), backTrack(prices, i+1, true, profit - prices[i]));
        }
    }

    record Context(int index, boolean hasStock){}
}
