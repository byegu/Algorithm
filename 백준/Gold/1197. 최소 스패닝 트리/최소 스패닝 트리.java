import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static int E;
	
	static class Node implements Comparable<Node>{
		int to;
		int cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static boolean[] visited;
	static ArrayList<Node>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[V+1];
		visited = new boolean[V+1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, cost));
			graph[to].add(new Node(from, cost));
		}
		
		prim();
	}
	
	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1,0));
		
		int result = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (visited[now.to]) {
				continue;
			}
			
			visited[now.to] = true;
			
			result += now.cost;
			
			for (Node next : graph[now.to]) {
				if (!visited[next.to]) {
					pq.offer(next);
				}
				
			}
		}
		System.out.println(result);
	}
}
