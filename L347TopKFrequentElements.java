import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

class L347TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        return topKFrequentHashMap(nums, k);
        
    }
    //Time: 
    //Space: 
    //With MinHeap
    public int[] topKFrequentMinHeap(int[] nums, int k) {
       TreeSet<List<Integer>> minHeap  = new TreeSet<List<Integer>>((i,j)-> i.get(1) - j.get(1));
       HashMap<Integer,Integer> frequencies = new HashMap<>();
       for (int i = 0; i < nums.length; i++) {
        int current= nums[i];
        frequencies.put(current, frequencies.getOrDefault(current, 0)+1);
        List<Integer> couple = new ArrayList<>();
        couple.add(current);
        couple.add(frequencies.get(current));
        if(minHeap.size() < k){
            minHeap.add(couple);
        } else{
            List<Integer> first = minHeap.first();
            if(first.get(1) < frequencies.get(current)){
                minHeap.remove(first);
                minHeap.add(couple);
            }
        }
       }
       int[] result = new int[k];
        for (int i = 0; i < result.length; i++) {
            result[i]= minHeap.first().get(0);   
            minHeap.remove(minHeap.first());
        }
        return result;
        // go throught the elements of the list one by one, add the 
    }

    //Time: O(nlogn)
    //Space: O(n)
    //With hashmap the sorting then taking a sublist 
    public int[] topKFrequentHashMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i],  map.getOrDefault(nums[i], 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> entryListSortedDesc =  map.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .toList();
        return entryListSortedDesc.subList(0, k).stream().mapToInt(Map.Entry::getKey).toArray();
    }
}
