package secondPasss;

import java.util.HashMap;
import java.util.Map;

public class L1EasyTwoSum {
    public int[] twoSum(int[] nums, int target) {
        return twoSumHashMap(nums, target);
    }
    
    //Intuition: 
    // Start 
    // -> go through the Array's elements 
    // -> For each element 
    //      -> compute the result of target - nums[i] 
    //      -> If the HashMap contains a key equals to the result, then return current index and value of that key 
    //      -> else, add to the HashMap nums[i] as a key and the index as a value
    // -> if you reach the end of nums return null 
    // -> End
    //Time: O(n) -> we go through the array once.
    //Space: O(n) for the Map
    private int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.get(sub) != null) {
                return new int[] { map.get(sub),i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
