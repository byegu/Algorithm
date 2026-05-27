import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char paren = s.charAt(i);
            
            if (i == 0 && paren == ')') {
                return false;
            }
            
            if (paren == '(') {
                stack.push(paren);
            } else if (!stack.isEmpty() && paren == ')') {
                stack.pop();
            } 
        }
        
        if (!stack.isEmpty()) {
            return false;
        }
        return answer;
    }
}