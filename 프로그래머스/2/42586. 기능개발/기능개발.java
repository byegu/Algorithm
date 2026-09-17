import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = progresses.length-1; i >= 0; i--) {
            stack.push(progresses[i]);
        }
        
        List<Integer> list = new ArrayList<>();
        
        int day = 0;
        int idx = 0;
        int out = 0;
        
        while (!stack.isEmpty()) {
            int task = stack.peek();
            task = task + speeds[out] * day;
            if (task >= 100) {
                stack.pop();
                out++;
                idx++;
                
                if (stack.isEmpty()) {
                    list.add(idx);
                }
                continue;
            }
            
            if (idx > 0) {
                list.add(idx);
                idx = 0;
            }
            day++;
        }
        
        int[] answer = new int[list.size()];
        
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}