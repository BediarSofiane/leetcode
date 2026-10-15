import java.util.ArrayList;
import java.util.List;

class L39CombinationSum {
    //Approach: Backtrack
    //Intuition: Go through the numbers one by one and add up untill reaching target or surpassing target, if we reach target, add the list to result.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList();
       // backtrackV1(candidates, result, target, 0, new ArrayList<>());
        backtrackV2(candidates, result, 0, target, 0, new ArrayList<>());
        return result;
    }

    //In this version we reduce the selected value from the searched target each time we recurse.
    private void backtrackV1(int[] candidates, List<List<Integer>> result, int target, int index,
            List<Integer> currentList) {
        if (target == 0) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            currentList.add(candidates[i]);
            backtrackV1(candidates, result, target - candidates[i], i, currentList);
            currentList.remove(currentList.size() - 1);
        }
    }
    
     //In this version we accumulate the sum until reaching or surpassing target without changine the target valuef.
    private void backtrackV2(int[] candidates, List<List<Integer>> result, int sum,int target, int index, List<Integer> currentList) {
        if (target == sum) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        if (target < sum) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            currentList.add(candidates[i]);
            backtrackV2(candidates, result ,sum + candidates[i],target, i, currentList);
            currentList.remove(currentList.size() - 1);
        }
    }
}