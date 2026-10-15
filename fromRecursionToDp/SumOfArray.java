package fromRecursionToDp;

// This is an example to practice Recursion.
//Sum all array elements from i to the end.
public class SumOfArray {

    public int sum(int[] nums, int i) {
        return sumRecursive(nums, i);
    }

    // Time: O(n) -> where n is the number of elements between i and end
    // Space: O(n) -> Stack
    private int sumRecursive(int[] nums, int i) {
        if (i >= nums.length)
            return 0;
        return nums[i] + sumRecursive(nums, i + 1);
    }

    public static void main(String[] args) {
        SumOfArray sumOfArray = new SumOfArray();
        int res = sumOfArray.sum(new int[] { 1, 2, 3, 3, 4, 3, 2, 1 }, 3);
        System.out.println(res);
    }

}
