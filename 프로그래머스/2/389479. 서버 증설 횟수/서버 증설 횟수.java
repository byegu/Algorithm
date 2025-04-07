import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] players, int m, int k) {
        int expansion = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < players.length; i++) {
            int need = players[i] / m;

            while (!queue.isEmpty() && queue.peek() <= i) {
                queue.poll();
            }

            int current = queue.size();
            
            if (current < need) {
                int server = need - current;
                expansion += server;
                for (int j = 0; j < server; j++) {
                    queue.offer(i + k);
                }
            }
        }
        int answer = expansion;
        return answer;
    }
}