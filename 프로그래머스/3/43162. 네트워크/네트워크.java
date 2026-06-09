import java.util.*;

class Solution {
    class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean[] visited;
    static int count = 0;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        count = 0;
        traversal(n, computers);
        answer = count;
        return answer;
    }
    
    public void traversal(int n, int[][] computers) {
        visited = new boolean[n]; 
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, 0, n, computers);
            }
        }
    }
    
    public void bfs(int r, int c, int n, int[][] computers) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        visited[r] = true;
        int result = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();
            r = p.r;
            result++;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && computers[r][i] == 1) {
                    q.offer(new Point(i, 0));
                    visited[i] = true;
                }
            }
        }
        
        if (result > 0) {
            count++;
        }

        return;
    }
}