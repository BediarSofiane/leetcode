package secondPasss;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L3MediumLongestSubstringWithougRepeatingCharacters {
    //Intuition: Start 
    // -> Put all seen characters in seen set, and grow the window while the next character is not seen. 
    // -> once you hit a character that is seen, start the window there 
    // -> every time you change the window, compute the max between this window size and old windows sizes.
    // -> continue until the end
    // -> return max window size
    // -> End
    // Time: O(n) -> We go through the array once.
    // Space: O(n) -> The hashset might grow to the same size as the array if all characters are different.
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0 || n == 1) {
            return n;
        }
        int left = 0;
        int right = left + 1;
        int result = 1;
        //key is character and value is it's index in the string
        Map<Character, Integer> seen = new HashMap<>();
        seen.put(s.charAt(0), 0);
        while (right < n) {
            Integer seenIndex = seen.get(s.charAt(right));
            if (seenIndex != null) {
                result = Math.max(result, right - left);
                for(int i=left ;i<= seenIndex; i++){
                    seen.remove(s.charAt(i));
                }
                left = seenIndex + 1;
            } 
            seen.put(s.charAt(right), right);
            right++;
            
            
        }
        return Math.max(result, right - left);
    }
}
