import java.util.HashMap;
import java.util.Map;

class L567PermutationInString {
    // intuition: Create a pointer in s2 that goes increasingly, and a pointer in s1
    // that goes decreasingly. Only start move the s1 indice backward while the
    // characters in s2 are matching the characters in s1 in reverse direction. I at
    // some point they stop matching before reaching the end of s1, return the
    // pointer of s1 to the end and start again. if you finish s1 completely then return true, otherwise return false.
    //Time: O(n2)
    //Space: O(1)
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        Map<Character, Integer> s1Frequencies = new HashMap();
        Map<Character, Integer> s2Count = new HashMap();
        for (char c : s1.toCharArray()) {
            s1Frequencies.put(c, s1Frequencies.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int count = 0;
        while (right < n2) {
            char current = s2.charAt(right);
            Integer frequencyInS1 = s1Frequencies.getOrDefault(current, 0);
            int frequencySoFar = s2Count.getOrDefault(current, 0);
            if (frequencySoFar < frequencyInS1) {
                s2Count.put(current, frequencySoFar + 1);
                count++;
                if (count == n1) {
                    return true;
                }
            } else {
                if (count > 0) {
                    char leftChar = s2.charAt(left);
                    s2Count.put(leftChar, s2Count.get(leftChar) -1);
                }
                count = count - 1;
                left++;
            }
            right++;
        }

        return false;
    }
}