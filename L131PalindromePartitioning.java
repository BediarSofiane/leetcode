import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class L131PalindromePartitioning {
    // Intuition: Start from an empty state and for each step, enumerate all
    // possible subString that are left to be taken, if the substring is not a
    // palindrom, stop, otherwise, add the current substring to the current list,
    // when no more substring available, add the current list to the result.
    // Time: O(n^n)
    // Space: O()
    Map<int[], Boolean> memory = new HashMap();
    boolean[][] palindromes;

    public List<List<String>> partition(String s) {
        List<List<int[]>> tmpResult = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), tmpResult);
        List<List<String>> result = new ArrayList<>();
        for (List<int[]> rangeList : tmpResult) {
            List<String> stringList = new ArrayList<>();
            for (int[] range : rangeList) {
                stringList.add(s.substring(range[0], range[1] + 1));
            }
            result.add(stringList);
        }
        return result;
    }
    
    public List<List<String>> partitionWithPalindromDP(String s) {
        palindromes = new boolean[s.length()][s.length()];
        initPalindromes(s);
        List<List<String>> result = new ArrayList<>();
        backtrackDP(s, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void initPalindromes(String s) {
        for (int i = 0; i < palindromes.length-1; i++) {
            palindromes[i][i] = true;
            palindromes[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        palindromes[s.length() - 1][s.length() - 1]= true;
        int j= 2;
        while(j<s.length()){
            for(int i=0; i + j < s.length(); i++){
                palindromes[i][i+j] = s.charAt(i) == s.charAt(i+j) && palindromes[i + 1][i+j - 1];
            }
            j++;
        }
    }

    private void backtrack(String s, int start, List<int[]> currentList, List<List<int[]>> result) {
        if (start >= s.length()) {
            result.add(new ArrayList(currentList));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                currentList.add(new int[] { start, i });
                backtrack(s, i + 1, currentList, result);
                currentList.remove(currentList.size() - 1);
            } else {
                continue;
            }
        }
    }
    
    private void backtrackDP(String s, int start, List<String> currentList, List<List<String>> result) {
        if (start >= s.length()) {
            result.add(new ArrayList(currentList));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (palindromes[start][i]) {
                currentList.add(s.substring(start, i+1));
                backtrackDP(s, i + 1, currentList, result);
                currentList.remove(currentList.size() - 1);
            } else {
                continue;
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        Boolean fromMemory = memory.get(new int[] { start, end });
        if (fromMemory != null) {
            return fromMemory;
        }
        int left = start;
        int right = end;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                memory.put(new int[] { start, end }, false);
                return false;
            }
            left++;
            right--;
        }
        memory.put(new int[] { start, end }, true);
        return true;
    }
    

}