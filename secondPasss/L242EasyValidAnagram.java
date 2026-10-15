package secondPasss;

public class L242EasyValidAnagram {
    public boolean isAnagram(String s, String t) {
        return isAnagramBucketCount(s, t); 
    }

    //Intuition: 
    // Start 
    // -> If the strings do not have the same size, return false 
    // -> count characters for both strings and store them in 26 (lower case english letters number) sized arrays 
    // -> If both Arrays are indetical, then return true, otherwiser return false 
    // -> End
    //Time: O(n) where n is the length of the both strings
    //Space: O(1) always 2*26 sized arrays to use
    private boolean isAnagramBucketCount(String s, String t){
        if(s.length() != t.length()){
            return false;
        }
        int[] countS = new int[26];
        int[] countT = new int[26];
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            countS[c - 'a']++;
        }
        for(int i=0; i<t.length(); i++){
            char c = t.charAt(i);
            countT[c - 'a']++;
        }
        for(int i=0; i<26; i++){
            if(countS[i] != countT[i]){
                return false;
            }
        }
        return true;

    }
}
