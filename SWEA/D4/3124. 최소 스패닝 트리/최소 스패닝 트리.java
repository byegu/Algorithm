import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int V, E;

	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static ArrayList<Node>[] graph;
	static StringBuilder sb;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();
			sb.append("#" + t + " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			visited = new boolean[V+1];
			graph = new ArrayList[V+1];
			
			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				graph[from].add(new Node(to, cost));
				graph[to].add(new Node(from, cost));
			}
			
			prim();
			System.out.println(sb);
		}
	}
	
	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1.cost, o2.cost));
		pq.offer(new Node(1, 0));
		
		long result = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (visited[now.to]) {
				continue;
			}
			
			visited[now.to] = true;
			result += now.cost;
			
			for (Node next : graph[now.to]) {
				if(!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		sb.append(result);
	}
}
