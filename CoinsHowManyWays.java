class CoinsHowManyWays {
    
    public int allNumberOfWays(int[] coins, int target){
        int[] result = new int[]{0};
        numberOfWays(coins, target, 0, result);
        return result[0];
    }
    public void numberOfWays(int[] coins, int target, int counter, int[] result){
        if(counter == target){
            result[0] ++;
            return;
        }
        if(counter > target){
            return;
        }
        for(int i=0; i< coins.length; i++){
            int coin = coins[i];
            numberOfWays(coins, target, counter + coin, result);
        }
    }
}