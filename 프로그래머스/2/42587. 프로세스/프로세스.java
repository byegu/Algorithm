import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Deque<int[]> q = new ArrayDeque<>();
        
        for (int i = 0; i < priorities.length; i++) {
            q.offer(new int[] {priorities[i], i});
        }
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            boolean flag = false;
            
            for (int[] next : q) {
                if (now[0] < next[0]) {
                    flag = true;
                    break;
                }
            }
            
            if (flag) {
                q.offerLast(now);
            } else {
                answer++;
                
                if (now[1] == location) {
                    return answer;
                }
            }
        }
        
        return answer;
    }
}