import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> count = new HashMap<>();
        
        for (String name : participant) {
            count.put(name, count.getOrDefault(name, 0) + 1);
        }
        
        for (String name : completion) {
            count.put(name, count.get(name) - 1);
        }
        
        for (String name : count.keySet()) {
            if (count.get(name) > 0) {
                answer = name;
            }
        }
        return answer;
    }
}