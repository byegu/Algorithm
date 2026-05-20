import java.util.*;

class Solution {
   
    static Map<Character, Integer> map;
    static int N;
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        map = new HashMap<>();
        N = skill.length();
        for (int i = 0; i < N; i++) {
            map.put(skill.charAt(i), i);
        }
        
        for (int i = 0; i < skill_trees.length; i++) {
            if (check(skill_trees[i])) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean check(String trees) {
        boolean[] isPossible = new boolean[N];
        int now = -1;
        for (int i = 0; i < trees.length(); i++) {
            char ch = trees.charAt(i);
            int idx = map.getOrDefault(ch, -1);
            
            if (idx == -1) {
                continue;
            } else if (now + 1 == idx) {
                now = idx;
            } else {
                return false;
            }
        }
        
        return true;
    }
}