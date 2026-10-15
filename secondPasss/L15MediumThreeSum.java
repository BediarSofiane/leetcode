package secondPasss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class L15MediumThreeSum {
    // Assumptions:
    // Array size is at least 3
    // Intuition:
    // Start
    // -> the first index goes from 0 to n - 2, and for each value looks in the
    // array that follows for any two indeces that satisy the equation
    // nums[firstIndex] + nums[second] + nums[third]= 0
    // -> for reach found indices, add the triplet to the result array
    // -> End
    // Time: O(n^2) -> first will go through n, for each first we visit the array to the left -> n + (n-1) + (n-2) + ... = n(n+1)/2 -> O(n2)
    // Space: O(n^2) -> for each map we use.
    public List<List<Integer>> threeSum(int[] nums) {
        //return threeSumNonSorted(nums);
    }
    //Example: nums = [-1,0,1,2,-1,-4]
    //n=6
    //first=1
    //num[first] = 0
    //target = 0
    //i = 4
    //nums[i] = -1
    //target - nums[i] = 1
    // visited = 1 -> [2], 2 -> [3], 
    //result = [[0,1,2], [0, 3, 4], [1, 2, 4], ]

    private List<List<Integer>> threeSumNonSorted(int[] nums) {
        Set<List<Integer>> results = new HashSet();
        int n = nums.length;
        for (int first = 0; first < n - 2; first++) {
            Map<Integer, Integer> visited = new HashMap();
            int target = 0 - nums[first];
            for (int i = first + 1; i < n; i++) {
                Integer candidate = visited.get(target - nums[i]);
                if (candidate != null) {
                    var result = Arrays.asList(nums[first], nums[candidate], nums[i]);
                    Collections.sort(result);
                    results.add(result);
                }
                visited.put(nums[i], i);
            }
        }
        return new ArrayList<>(results);
    }

    //Intuition: Sort the array before starting to loop over its elements. then for each first that is selected, never select the same first again, and no need to sort the result arrays, because they are already sorted.
    private List<List<Integer>> threeSumSorted(int[] nums) {
        Set<List<Integer>> results = new HashSet();
        int n = nums.length;
        Arrays.sort(nums);
        for (int first = 0; first < n - 2; first++) {
            //Only take one time each first value.
            if ((first - 1) >= 0 && nums[first - 1] == nums[first]) {
                continue;
            }
            Map<Integer, Integer> visited = new HashMap();
            int target = 0 - nums[first];

            for (int i = first + 1; i < n; i++) {
                Integer candidate = visited.get(target - nums[i]);
                if (candidate != null) {
                    var result = Arrays.asList(nums[first], nums[candidate], nums[i]);
                    results.add(result);
                }
                visited.put(nums[i], i);
            }
        }
        return new ArrayList<>(results);
    }
    

    private List<List<Integer>> threeSumSortedWithoutHashMap(int nums[]) {
        Set<List<Integer>> results = new HashSet();
        int n = nums.length;
        Arrays.sort(nums);
        for (int first = 0; first < n - 2; first++) {
            //Only take one time each first value.
            if ((first - 1) >= 0 && nums[first - 1] == nums[first]) {
                continue;
            }
            int second = first + 1;
            int third = n - 1;
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                if (sum == 0) {
                    results.add(Arrays.asList(nums[first], nums[second], nums[third]));
                    third--;
                    second++;
                } else if (sum > 0) {
                    third--;
                } else {
                    second++;
                }
            }
        }
        return new ArrayList<>(results);
    }
}
