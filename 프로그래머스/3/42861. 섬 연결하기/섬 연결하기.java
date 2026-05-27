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
    public int solution(int n, int[][] costs) {
        graph = new ArrayList[n];
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int cost = costs[i][2];
            
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }
        
        return prim(0, n);
    }
    
    public static int prim(int start, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.offer(new Node(start, 0));
        int total = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int to = now.to;
            int cost = now.cost;
            
            if (visited[to]) {
                continue;
            }
            
            visited[to] = true;
            total += cost;
            
            for (Node next : graph[to]) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }
        
        return total;
    }
}