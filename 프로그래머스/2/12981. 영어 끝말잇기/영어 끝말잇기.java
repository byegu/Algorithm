import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};
        Set<String> used = new HashSet<>();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            
            boolean duplicated = false;
            boolean invalid = false;
            
            if (i > 0) {
                String prev = words[i-1];
                char last = prev.charAt(prev.length()-1);
                char first = word.charAt(0);
                
                if (last != first) invalid = true;
            }
            
            if (used.contains(word)) duplicated = true;
            
            if (invalid || duplicated) {
                return new int[]{i % n + 1, i / n + 1};
            }
            
            used.add(word);
        }

        return new int[]{0, 0};
    }
}