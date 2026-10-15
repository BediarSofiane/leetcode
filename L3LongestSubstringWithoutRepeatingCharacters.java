import java.util.HashSet;
import java.util.Set;

public class L3LongestSubstringWithoutRepeatingCharacters {
    //Intuition: Start with two pointers at eh beginning of the string, while the new character is not in the string so far, move right, else move left.
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0;
        int right = 0;
        Set<Character> visited = new HashSet();
        int length = 0;
        while (right < n) {
            if (visited.contains(s.charAt(right))) {
                visited.remove(s.charAt(left));
                left++;
            }
            visited.add(s.charAt(right));
            right++;
            length = Math.max(length, visited.size());
        }
        return length;
        
    }
}
