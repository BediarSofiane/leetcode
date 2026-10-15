import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class L90SubsetsII {
    // Approach: Backrtracking
    // Intuition: Start with an empty list, Go over all elements of the array, for
    // each element, add it to the current list, save a copy of this list to result,
    // and recursively do the same thing with the current set and next element,
    // until reaching the end of the array. 
    // To avoid having duplicates, start by sorting the array to have all duplicates next to each other, and whenever the first occurence of the same element is processed skip the next ones.
    //Time:O(2^n + nlogn = 2^n)
    //Space: O(n) for the recursion stack depth. 
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        backTrack(nums, result, 0, new ArrayList<>());
        return result;
    }

    private void backTrack(int[] nums, List<List<Integer>> result, int index, List<Integer> currentList) {
        if(index > nums.length){
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            currentList.add(nums[i]);
            result.add(new ArrayList<>(currentList));
            backTrack(nums, result, i + 1, currentList);
            currentList.remove(currentList.size() - 1);
        }
    }
}