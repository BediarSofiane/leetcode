class L153FindMinimumInRotatedSortedArray {
    // Appraoch: Binary search
    // Intuition: Do a modified version of binary search by initiating two pointers
    // left and right, while left < right, get the middle element, if middle is
    // lower than right, then the middle is part of the smaller array, so we need to
    // look between left and middle recursively. If middle is higher than right,
    // this means that middle is in the bigger array, then we need to look between
    // min and right. keep going until left gets higher than right.
    //Time: O(logn)
    //Space: O(1)
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int min = Integer.MAX_VALUE;
        while (left <= right) {
            int middle = (left + right) / 2;
            min = Math.min(min, nums[middle]);
            if (nums[middle] < nums[right]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return min;

    }
}