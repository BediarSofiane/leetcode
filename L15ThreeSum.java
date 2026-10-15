import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class L15ThreeSum {
    // Intuition: Go over the elements of the array, for each element, consider it
    // as the first of the triplet, then look for all possible combinations of
    // second and third. stop the first pointer at length - 3. 
    // To find all combinations of 2 sum, usethe hashmap method.
    // 2. sort each combination before adding to the result set. 
    // 3. convert the result set to a list and return it.
    // Time:O(n^2)
    // Space: O(n)
    public List<List<Integer>> threeSumNotSorted(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < n - 2; i++) {
            int first = nums[i];
            Set<Integer> references = new HashSet();
            for (int j = i + 1; j < n; j++) {
                if (references.contains(-first - nums[j])) {
                    List<Integer> triplet = Arrays.asList(first, -first - nums[j], nums[j]);
                    Collections.sort(triplet);
                    result.add(triplet);
                }
                references.add(nums[j]);
            }

        }
        return new ArrayList<>(result);
    }
    
    // Intuition:Sort the array in order to avoid using a set that stores passed values.
    // Time:
    // Space: 
    public List<List<Integer>> threeSumSorted(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int first = nums[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    while(nums[left] == nums[left -1] && left < right){
                        left++;
                    }
                } else if (sum < 0) {
                    left++;
                     while(nums[left] == nums[left -1] && left < right){
                        left++;
                    }
                } else {
                    right--;
                    while (nums[right] == nums[right + 1] && left < right) {
                        right--;
                    }
                }
            }

        }
        return result;
    }
}