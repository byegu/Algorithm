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
    
    int[] dr = {0, 0, 1, -1};
    int[] dc = {-1, 1, 0, 0};
    boolean[][] visited;
    List<Integer> list;
    
    public int[] solution(String[] maps) {
        int[] answer = {}; 
        list = new ArrayList<>();
        traversal(maps);
        
        if (list.size() == 0) {
            return new int[]{-1};
        }
        Collections.sort(list);
        answer = list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return answer;
    }
    
    public void traversal(String[] maps) {
        visited = new boolean[maps.length][maps[0].length()];
        int result = 0;
        
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0 ; j < maps[i].length(); j++) {
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    result = bfs(i, j, maps);
                    
                    list.add(result);
                }
            }
        }
    }
    
    public int bfs(int r, int c, String[] maps) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        visited[r][c] = true;
        int result = maps[r].charAt(c) - '0';
        
        while(!q.isEmpty()) {
            Point p = q.poll();
            r = p.r;
            c = p.c;
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (!isIn(nr, nc, maps.length, maps[0].length())) {
                    continue;
                }
                
                if (!visited[nr][nc] && maps[nr].charAt(nc) != 'X') {
                    result += maps[nr].charAt(nc) - '0';
                    q.offer(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        
        return result;
    }
    
    public boolean isIn(int r, int c, int n, int m) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}