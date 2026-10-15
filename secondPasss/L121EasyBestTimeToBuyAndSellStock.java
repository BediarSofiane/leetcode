package secondPasss;

public class L121EasyBestTimeToBuyAndSellStock {
    //Assumptions: 
    // Stock prices are all positive and different than zero.
    // Array's size is at least 2.
    //Intuition: 
    // Start 
    // -> intialize the edges of the sliding window to the first and second elements -> keep track of the maximum distance the window gives between the left and right edges 
    // -> keep moving right pointer and updatiing the max profit so far, util you reach a value that is less than the current left 
    // -> the current left will become this new value 
    // -> continue moving right and updating left until reaching the end of the array
    // -> End
    //Time: O(n) -> we go once through all elements of the array
    //Space: O(1) -> no added data strucutre is needed, nothing grows when n grows.
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;
        int left = 0;
        int right = 1;
        while (right < n) {
            int profit = prices[right] - prices[left];
            if (profit < 0) {
                left = right;
            } else {
                maxProfit = Math.max(maxProfit, profit);
            }
            right++;
        }
        return maxProfit;
    }
    //Examples: prices = [7,1,5,3,6,4]
}