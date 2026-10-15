package secondPasss;

import java.util.Stack;

public class L20EasyValidParentheses {
    public boolean isValid(String s) {
        return isValidFirstTry(s);   
    }

    private boolean isValidFirstTry(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.add(c);
            } else if (stack.empty() || c == ')' && stack.pop() != '(' || c == '}' && stack.pop() != '{'
                    || c == ']' && stack.pop() != '[') {
                return false;
            }
        }
        return stack.empty();
    }
}
