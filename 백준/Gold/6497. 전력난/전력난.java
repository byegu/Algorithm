import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int m; // 집의 수
	static int n; // 길의 수(간선)

	static ArrayList<Node>[] graph;
	static boolean[] visited;

	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
	}
	static int sum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if (m == 0 && n == 0) {
				break;
			}

			graph = new ArrayList[m + 1];
			visited = new boolean[m + 1];
			
			for (int i = 0; i <= m; i++) {
				graph[i] = new ArrayList<>();
			}
			sum = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				graph[from].add(new Node(to, cost));
				graph[to].add(new Node(from, cost));
				sum += cost;
			}
			
			prim();
		}
	}
	
	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost , o2.cost));
		pq.offer(new Node(1, 0));
		int result = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(visited[now.to]) {
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
		
		System.out.println(sum - result);
	}
}
