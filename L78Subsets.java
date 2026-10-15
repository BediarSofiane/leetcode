import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class L78Subsets {
    //Approach: Backtracking recursively
    //Intuition: 
    public List<List<Integer>> subsetsDfs(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int i = 0; i< nums.length; i++){
            dfs(nums, i, result, new ArrayList());
        }
        return result;
    }

    private void dfs(int[] nums, int index, List<List<Integer>> result, List<Integer> subset) {
        subset.add(nums[index]);
        result.add(new ArrayList<>(subset));
        for (int i = index + 1; i < nums.length; i++) {
            dfs(nums, i, result, new ArrayList(subset));
        }
    }

    public List<List<Integer>> subsetsBitManipulation(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i <= (1 << n - 1); i++) {
            List<Integer> currentList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    currentList.add(nums[j]);
                }
            }
            result.add(currentList);
        }
        return result;
    }
}