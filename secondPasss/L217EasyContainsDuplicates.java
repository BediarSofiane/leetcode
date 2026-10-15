package secondPasss;

import java.util.HashSet;
import java.util.Set;

public class L217EasyContainsDuplicates {
    public boolean hasDuplicate(int[] nums) {
        return containsDuplicate(nums);
    }
    
    public boolean containsDuplicate(int[] nums) {
        return containsDuplicateHashSet(nums);
    }
    
    //Intuition: 
    // Start
    // -> Go through elements of the Array 
    // -> put each element in a Set if it's not already their 
    // -> when element is already in the set, return true 
    // -> if you reach the end, then return false 
    // -> End
    //Time: O(n)
    //Space: O(n)
    private boolean containsDuplicateHashSet(int[] nums) {
        Set<Integer> seen = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            if (!seen.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
    
}
