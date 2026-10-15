import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class L46Permutations {
    // Approach: BackTracking
    // Intuition: Start with an empty list go over elements of nums, for each
    // element, either start with it or not, when you start from an index you back
    // track on all possibilities for the next element. which are all elements of
    // the array except those already selected.
     public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numsAsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        backTrack(numsAsList, result, new ArrayList());
        return result;
    }

    //Naive version
    private void backTrack(List<Integer> numsAsList, List<List<Integer>> result, List<Integer> currentPermutation) {
        if (numsAsList.isEmpty()) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }
        int size = numsAsList.size();
        for (int i = 0; i < size; i++) {
            currentPermutation.add(numsAsList.get(0));
            int tmp = numsAsList.get(0);
            numsAsList.remove(0);
            backTrack(numsAsList, result, currentPermutation);
            numsAsList.add(tmp);
            currentPermutation.remove(currentPermutation.size() - 1);
        }
    }

     public List<List<Integer>> permuteUsingSwaps(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(nums, result, 0);
        return result;
    }
     //Using swaps
     private void backTrack(int[] nums, List<List<Integer>> result, int index) {
        if (index == nums.length) {
            result.add(Arrays.stream(nums).boxed().toList());
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            backTrack(nums, result, index + 1);
            swap(nums, i, index);

        }
    }

    private void swap(int[] nums, int i, int index) {
        int tmp = nums[i];
        nums[i] = nums[index];
        nums[index] = tmp;
    }
     
    public List<List<Integer>> permuteUsingArrays(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(nums, result, new int[nums.length], 0);
        return result;
    }

    //Naive version
    private void backTrack(int[] nums, List<List<Integer>> result, int[] currentPermutation, int index) {
        if (nums.length == 0) { // can also use if(index == currentPermutation.length)
            result.add(Arrays.stream(currentPermutation).boxed().toList());
        }
        for (int i = 0; i < nums.length; i++) {
            currentPermutation[index] = nums[i];
            int[] remaining = new int[nums.length - 1];
            int k = 0;
            for (int j = 0; j < nums.length; j++) {
                if (j != i) {
                    remaining[k] = nums[j];
                    k++;
                }
            }
            backTrack(remaining, result, currentPermutation, index + 1);
        }
    }
}