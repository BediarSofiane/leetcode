class L33SearchInRotatedSortedArray {
    //Approach: Binary search
    //Intuition: an adapted version of binary search. when the middle is got, we can be in one of the following cases:
    //1. middle is smaller than right => the array from middle to right is the sorted one, and from left to middle is the non sorted one. 3 cases are possible:
    //1.1 target is smaller than middle  => that means that target can't be between middle and right => look between left and middle -1.
    //1.2 target is higher than middle but smaller than right => then target is between middle and target if it exists, and cannot be between left and middle.
    //1.3 target is higher than middle and higher than right => then it is between left and middle -1 if it exists.
    //2. middle is higher than right => the array from middle to right is not sorted and the array between left and middle is sorted. Same logic for the 3 cases:
    // 2.1 target is smaller than middle and smaller than right => then it is between middle and right if it exists.
    //2.2 target is smaller than middle and higher than right => then its is between left and middle -1 if it exists.
    //2.3 target is higher than middle => then it is between left and middle -1 if it exists.
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] < nums[right]) {
                if (nums[right] == target) {
                    return right;
                }
                if (target < nums[middle] || target > nums[right]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (nums[left] == target) {
                    return left;
                }
                if (target > nums[middle] || target < nums[left]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }
}