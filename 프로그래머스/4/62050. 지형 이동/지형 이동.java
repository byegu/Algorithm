import java.util.*;

class Solution {
    static class Node {
        int to;
        int cost;
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    static List<Node>[] graph;
    static boolean[] visited;
    
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int N;
    public int solution(int[][] land, int height) {
        N = land.length;
        graph = new ArrayList[N*N];
        visited = new boolean[N*N];
        
        for (int i = 0; i < N*N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    
                    
                    int from = r*N + c;
                    int to = nr*N + nc;

                    if (!isIn(nr, nc)) {
                        continue;
                    }
                    
                    if (Math.abs(land[r][c] - land[nr][nc]) <= height) {
                        graph[from].add(new Node(to, 0));
                        graph[to].add(new Node(from, 0));  
                    } else {
                        graph[from].add(new Node(to, Math.abs(land[r][c] - land[nr][nc])));
                        graph[to].add(new Node(from, Math.abs(land[r][c] - land[nr][nc])));
                    }
                }
            }
        }
        
        return prim();
    }
    public int prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.offer(new Node(0, 0));
        int total = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (visited[now.to]) {
                continue;
            }
            
            visited[now.to] = true;
            total += now.cost;
            
            for (Node next : graph[now.to]) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }
        
        return total;
    }
    
    public boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}