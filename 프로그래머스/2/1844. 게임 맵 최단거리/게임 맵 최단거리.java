import java.util.*;

class Solution {
    static int dr[] = {0,0,-1,1};
    static int dc[] = {-1, 1, 0, 0};
    
    static int N, M;
    static class Point {
        int r;
        int c;
        int dist;
        
        Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length;
        answer = bfs(maps);
        return answer;
    }
    
    public int bfs(int[][] maps) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 0));
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        while(!q.isEmpty()) {
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            int dist = p.dist;
            
            if (r == N-1 && c == M-1) {
                return dist+1;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (!isIn(nr, nc)) {
                    continue;
                }
                
                if (!visited[nr][nc] && maps[nr][nc] == 1) {
                    q.offer(new Point(nr, nc, dist+1));
                    visited[nr][nc] = true;
                }
            }
        }
        
        return -1;
    }
    
    public boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}