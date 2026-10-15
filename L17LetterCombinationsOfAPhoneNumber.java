import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class L17LetterCombinationsOfAPhoneNumber {
    Map<Character, List<Character>> digitsToChars = new HashMap();

    public List<String> letterCombinations(String digits) {
        fillMap(digitsToChars);
        int n = digits.length();
        List<String> result = new ArrayList<>();
        backTrack(digits, 0, "", result);
        return result;
    }

    private void backTrack(String digits, int index, String currentString, List<String> result) {
        if (currentString.length() == digits.length()) {
            result.add(currentString);
            return;
        }
        char currentDigit = digits.charAt(index);
        for (char c : digitsToChars.get(currentDigit)) {
            backTrack(digits, index + 1, currentString + c, result);
        }
    }

    private void fillMap(Map<Character, List<Character>> digitsToChars) {
        digitsToChars.put('2', Arrays.asList('a', 'b', 'c'));
        digitsToChars.put('3', Arrays.asList('d', 'e', 'f'));
        digitsToChars.put('4', Arrays.asList('g', 'h', 'i'));
        digitsToChars.put('5', Arrays.asList('j', 'k', 'l'));
        digitsToChars.put('6', Arrays.asList('m', 'n', 'o'));
        digitsToChars.put('7', Arrays.asList('p', 'q', 'r', 's'));
        digitsToChars.put('8', Arrays.asList('t', 'u', 'v'));
        digitsToChars.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    
    // Intuition: Add the queue all the strings constructed so far (starting from an
    // empty string), for each digit to process, get all corrsponding character, and
    // get all strings already in the queue and add the current character to them,
    // then put the result back in the queue. by the end the queue will contain all
    // combinations
    //Time: O(4^n)
    //Space: O(4^n) If we count the queue. O(1) if we don't
    public List<String> letterCombinationsBFS(String digits) {
        if(digits.length() == 0){
            return new ArrayList();
        }
        fillMap(digitsToChars);
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        int i = 0;
        while (i < digits.length()) {
            char currentDigit = digits.charAt(i++);
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String currentString = queue.poll();
                for (char c : digitsToChars.get(currentDigit)) {
                    queue.offer(currentString + c);
                }
            }
        }
        return new ArrayList<>(queue);
    }
}