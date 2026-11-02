import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Deque<int[]> q = new ArrayDeque<>();
        
        for (int i = 0; i < priorities.length; i++) {
            q.offer(new int[]{priorities[i], i});
        }
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            boolean flag = false;
            
            for (int[] next : q) {
                if (now[0] < next[0]) { // 현재 프로세스가 이후에 나온 프로세스보다 우선순위가 낮으면
                    flag = true;
                    break;
                }
            }
            
            if (flag) {
                q.offerLast(now);
            } else { // 현재 프로세스가 우선순위가 가장 높은 경우
                answer++; // 실행 순서
                
                if (now[1] == location) {
                    return answer;
                }
            }
        }
        return answer;
    }
}