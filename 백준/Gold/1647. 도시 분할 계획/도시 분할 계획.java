import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int result;

	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static ArrayList<Node>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[to].add(new Node(from, cost));
			graph[from].add(new Node(to, cost));
		}


		prim();
	}

	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		pq.offer(new Node(1, 0));
		int result = 0;
		int cost = 0;
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (visited[now.to]) {
				continue;
			}

			visited[now.to] = true;

			result += now.cost;
			cost = Math.max(cost, now.cost);

			for (Node next : graph[now.to]) {
				if (!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		result -= cost;
		System.out.println(result);
	}

}
