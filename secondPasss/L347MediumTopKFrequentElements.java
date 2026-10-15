package secondPasss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

//Done: implemented two strategies: Count (using HashMap) then sort -> O(nlogn), Count then use a bucket sort strategy -≥ O(n) 
//TODO: implement: Count and put in a min heap
//TODO: implement: Count and put in a max heap  

public class L347MediumTopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        // return topKFrequentHashMap(nums, k )
        return topKFrequentBucketSorting(nums, k);
    }

    // Time complexity: O(n) + O(nlogn) + O(k<n) = O(nlogn)
    // Space complexity: O(n) for the HashMap
    // Intuition:
    // Start
    // -> Count frequencies using a HashMap
    // -> Sort the HashMap entries by value in DESC order
    // -> Limit to K elements
    // -> return as an Array
    // -> End
    private int[] topKFrequentHashMap(int[] nums, int k) {
        if (nums.length == 0) {
            return null;
        }
        if (k > nums.length) {
            return null;
        }
        Map<Integer, Integer> countMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }
        var topKFrequentAsList = countMap.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).limit(k)
                .map(e -> e.getKey()).collect(Collectors.toList());
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = topKFrequentAsList.get(i);
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    // Intuition:
    // Start
    // -> Count frequencies using a HashMap
    // -> create an array of frequencies
    // -> put each element (HashMap keys) in the frequencies array at the index
    // matching its frequency
    // -> go through frequencies array and return k elements from last to first.
    // -> End
    private int[] topKFrequentBucketSorting(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }
        List<Integer>[] countArray = new List[nums.length + 1];
        for (Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (countArray[entry.getValue()] == null) {
                countArray[entry.getValue()] = new ArrayList<Integer>();
            }
            countArray[entry.getValue()].add(entry.getKey());
        }
        int[] result = new int[k];
        int found = 0;
        int i = countArray.length - 1;
        while (i > 0 && found < k) {
            if (countArray[i] == null) {
                i--;
                continue;
            }
            int counter = 0;
            while (counter < countArray[i].size() && found < k) {
                result[found++] = countArray[i].get(counter);
                counter++;
            }
            i--;
        }
        return result;
    }
}
