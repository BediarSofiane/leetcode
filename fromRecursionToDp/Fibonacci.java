package fromRecursionToDp;

//FIBONACCI is the classical problem that demonstrates the journey from recursion to DP. 
// Steps: 
// 1. Brute force recursion (recursion without memoization)
// 2. Identify which elements get computed multiple times (overlapping subproblems) -> Add memoization -> Top down
// 3. Switch to Tabulation -> Buttome up -> reverse the logic FROM : To solve n I need solution of n-1 ->  TO: I have the solution for 0 what is the solution for 1, then 2, then 3, ..., then n.
// 4. Space optimization -> Analyse the dependency relationship and only keep the needed spaces istead of a full array.

public class Fibonacci {
    public int fib(int n) {
        long start = System.currentTimeMillis();
        // int res = fibBruteForce(n);
        // int res = fibMemoization(n, new Integer[n + 1]);
        // int res = fibDp(n);
        int res = fibDpSpaceOptimized(n);
        long end = System.currentTimeMillis();
        System.out.println("took " + (end - start) + "ms");
        return res;
    }

    // Step 1: Brute force recursion
    // Time: O(2^n) -> recursion with depth n and 2 branches per layer.
    // Space: O(n) -> stack
    private int fibBruteForce(int n) {
        if (n <= 1)
            return n;
        int result = fibBruteForce(n - 1) + fibBruteForce(n - 2);
        return result;
    }

    // Step 2: Memoization -> Top down
    // Time:O(n) -> for every n we only compute fib(n) once.
    // Space: O(2n) -> stack + array memory.
    private int fibMemoization(int n, Integer[] memo) {
        if (n <= 1)
            return n;
        if (memo[n] != null) {
            return memo[n];
        }
        memo[n] = fibMemoization(n - 1, memo) + fibMemoization(n - 2, memo);
        return memo[n];
    }

    // Step 3: Tabulation -> Bottom up
    // Time: O(n)
    // Space: O(n)
    private int fibDp(int n) {
        if (n <= 1)
            return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Step 4: Space optimization
    // Time: O(n)
    // Space: O(1)
    private int fibDpSpaceOptimized(int n) {
        if (n <= 1)
            return n;
        int a = 0, b = 1;
        int c;
        for (int i = 2; i <= n; i++) {
            c = b + a;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fib(8));

    }
}
