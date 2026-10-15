package fromRecursionToDp;

public class L198MediumHouseRober {
    public int rob(int[] nums) {
        // return robRecursiveBackward(nums, nums.length - 1);
        int n = nums.length;
        // return robRecursiveBackwardMemo(nums, n - 1, new Integer[n]);
        return robRecursiveForwardWithMemo(nums, 0, new Integer[n]);
    }

    // This is the recursive method that resulted from the first principle thinking
    // so it is backward from last action to the base case.
    // returns: the maximum amount that can be robed from the given houses 0 to n -
    // 1.
    // assumes: the maximum amount that can be robed from houses 0 to i-1 and the
    // maximum amount that can be robed from houses 0 to i-2 are correctly computed
    // base case: maximum amount that can be robed for 0 or negative number of
    // houses is 0
    // transition: let robed(i-2) be the maximum amount robed from houses between 0
    // and i-2 and robed(i-1) be the maximum amount fored from houses between 0 and
    // i-1, the result is max(robed(i-2) + amount in house i, robed(i-1)).
    // derived by last action: last action is either to rob last house or to not rob
    // it:
    // if we rob last house, the result is the amount of what has been robed until
    // house precedent house exclusive + the amount robed from last house.
    // if we don't rob the last house, the result is the amount of whas has been
    // robed until precendent house.
    // the final result is max of the results of these two choices.
    public int robRecursiveBackward(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        return Math.max(robRecursiveBackward(nums, i - 1),
                robRecursiveBackward(nums, i - 2) + nums[i]);
    }

    private int robRecursiveBackwardMemo(int[] nums, int i, Integer[] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        memo[i] = Math.max(robRecursiveBackwardMemo(nums, i - 1, memo),
                robRecursiveBackwardMemo(nums, i - 2, memo) + nums[i]);
        return memo[i];
    }

    private int robTabulationOfBackwardRecursion(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    public int robTabulationOfBackwardRecursionSpaceOptimized(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }

        int previous = nums[0];
        int current = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int tmp = current;
            current = Math.max(previous + nums[i], current);
            previous = tmp;
        }
        return current;
    }

    // This is the optional transformation of the backward first principle solution
    // to make the code go forward instead of backward which is harder to understand
    // as a code (escpecially for people who don't solve it the way you did, but
    // start with first action)
    public int robRecursiveForward(int[] nums, int i) {
        if (i >= nums.length)
            return 0;
        return Math.max(robRecursiveForward(nums, i + 1), robRecursiveForward(nums, i + 2) + nums[i]);
    }

    public int robRecursiveForwardWithMemo(int[] nums, int i, Integer[] memo) {
        if (i >= nums.length) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        memo[i] = Math.max(robRecursiveForwardWithMemo(nums, i + 1, memo),
                robRecursiveForwardWithMemo(nums, i + 2, memo) + nums[i]);
        return memo[i];
    }

    public static void main(String[] args) {
        L198MediumHouseRober houseRober = new L198MediumHouseRober();
        int res = houseRober.rob(new int[] { 2, 1, 1, 2 });
        System.out.println(res);

    }
}
