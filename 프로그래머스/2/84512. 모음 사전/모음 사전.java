import java.util.*;

class Solution {
    String[] vowels = {"A", "E", "I", "O", "U"};
    int answer = 0;
    int count = 0;
    
    public int solution(String word) {
        dfs("", word);
        return answer;
    }
    
    public void dfs(String current, String target) {
        if (current.length() > 5) {
            return;
        }
        
        if (!current.equals("")) {
            count++;
        }
        
        if (current.equals(target)) {
            answer = count;
            return;
        }
        
        for (int i = 0; i < vowels.length; i++) {
            dfs(current + vowels[i], target);
        }
    }
}