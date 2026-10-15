package fromRecursionToDp;

public class L70EasyClimbingStairs {
    public int climbStairs(int n) {
        long start = System.currentTimeMillis();
        // return climbStairsRecursive(n);
        int[] memo = new int[n + 1];
        memo[0] = memo[1] = 1; // This works but makes the climbStairsRecursiveWithMemory stateful, because it
                               // relyes on the array being well initialized, whereas if it set it itself, it
                               // will be stateless and more resilient.
        int res = climbStairsRecursiveWithMemory(n, memo);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return res;
    }

    // returns: the number of all possible ways to climb n stairs
    // assumes: the number of all ways to climb n-1 stairs and n-2 stairs are
    // correctly computed using recursion
    // base case: number of all possible ways to climb 1 stair is 1, and the number
    // of all possible ways to climb 0 stairs is 1
    // transition: the result is the sum of n-1 result and n-2 result
    // Time: O(2^n) -> depth = n, branching factor = 2.
    // Space: O(n) -> depth of the recursion -> stack
    private int climbStairsRecursive(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }

    // Time: O(n) -> thanks to memo each element is computed once and returned only
    // each time its needed.
    // Space: O(n) -> depth of the recursion -> stack
    private int climbStairsRecursiveWithMemory(int n, int[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = climbStairsRecursiveWithMemory(n - 1, memo) + climbStairsRecursiveWithMemory(n - 2, memo);
        return memo[n];
    }

    // Intuition: Go bottom up, since we know the results of i=0 and j=1, we build
    // until we
    // have the result of n
    // Time: O(n) -> we go through an array of size n and do O(1) operation each
    // time.
    // Space: O(n) -> the dp array
    private int climbStairsTabulation(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Intuition: We only need the 2 latest values to continue building up the
    // solution, no need to keep track of older values than the last 2.
    // Time: O(n)
    // Space: O(1) -> the gain of tabulation with space optimization is in space
    // complexity.
    private int climbStairsTabulationWithSpaceOptimization(int n) {
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            int tmp = b;
            b = a + b;
            a = tmp;
        }
        return b;
    }

    public static void main(String[] args) {
        L70EasyClimbingStairs climbingStairs = new L70EasyClimbingStairs();
        int res = climbingStairs.climbStairs(40);
        System.out.println(res);
    }
}
