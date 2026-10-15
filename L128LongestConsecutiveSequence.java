import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class L128LongestConsecutiveSequence {
    // Ituition: Go over all elements of the array, for each element, try to find
    // the longest sequence starting from this element. save the longest sequence so
    // far and compare it to every sequence you find. return the longest sequence.
    //Time: O(n^3)
    //Space : O(1)
    public int longestConsecutiveBruteForce(int[] nums) {
        int n = nums.length;
        int longestSequence = 0;
        int currentSequence = 0;
        for (int i = 0; i < n; i++) {
            currentSequence = 1;
            int next = nums[i];
            int j = 0;
            while (j < n) {
                if (nums[j] == next + 1) {
                    currentSequence++;
                    next = nums[j];
                    if (currentSequence > longestSequence) {
                        longestSequence = currentSequence;
                    }
                    j = 0;
                } else {
                    j++;
                }
            }
        }
        return Math.max(currentSequence, longestSequence);
    }
    
    //Intuition: Sort the array first so that you will not have to go back in order to look for the next element in the sequence.
    //Time: O(n^2)
    //Space: O(1)
    public int longestConsecutiveSortedArray(int[] nums) {
        int n = nums.length;
        int longestSequence = 0;
        int currentSequence = 0;
        Arrays.sort(nums);
        int i = 0;
        while (i < n - longestSequence) {
            currentSequence = 1;
            int current = nums[i];
            int j = i+1;
            while (j < n) {
                if(nums[j] == current + 1){
                    currentSequence++;
                    current = nums[j];
                } else if(nums[j] != current){
                    break;
                }
                j++;
            }
            longestSequence = Math.max(longestSequence, currentSequence);
            i = j;
        }
        return longestSequence;
    }
    
    //Intuition: Put all elements of the array in a hashset, then for each element, while element + x is in the set, keep incrementing the counter.
    //Time: O(n)
    //Space: O(n) 
     public int longestConsecutiveHashSet(int[] nums) {
        int n = nums.length;
        int longestSequence = 0;
        Set<Integer> numsSet = new HashSet();
        for(int i=0; i< n; i++){
            numsSet.add(nums[i]);
        }
        for(int num : numsSet){
            if(numsSet.contains(num - 1)){
                continue;
            }
            int sequenceLength = 1;
            while(numsSet.contains(num + sequenceLength)){
                sequenceLength++;
            }
            longestSequence = Math.max(longestSequence, sequenceLength);
        }
        return longestSequence;
      
    }


}