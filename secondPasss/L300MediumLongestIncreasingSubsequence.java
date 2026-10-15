package secondPasss;

public class L300MediumLongestIncreasingSubsequence {
    
    // example: nums = [10,9,2,5,3,7,101,18]
    public int lengthOfLIS(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[] result = new int[1];
        result[0] = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            this.lengthOfLISBackTrack(nums, i, max, 0, result);
        }
        return result[0];

    }
    
    private void lengthOfLISBackTrack(int[] nums, int index, int max, int counter, int[] result) {
        if (index == nums.length) {
            return;
        }
        if (nums[index] > max) {
            result[0] = Math.max(result[0], counter + 1);
            lengthOfLISBackTrack(nums, index + 1, nums[index], counter + 1, result);
        } else {
            lengthOfLISBackTrack(nums, index + 1, max, counter, result);;
        }
        
    }
}
