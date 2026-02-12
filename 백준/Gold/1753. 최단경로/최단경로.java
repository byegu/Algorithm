import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static int E;
	static int start;
	static int[] dist;
	static ArrayList<Node>[] graph;
	static final int INF = Integer.MAX_VALUE;

	static class Node implements Comparable<Node> {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		start = Integer.parseInt(br.readLine());

		graph = new ArrayList[V + 1];
		dist = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = INF;
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[u].add(new Node(v, w));
		}

		dijkstra(start);

		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.cost > dist[cur.to]) {
				continue;
			}

			for (Node next : graph[cur.to]) {
				int newCost = dist[cur.to] + next.cost; 

				if (newCost < dist[next.to]) {
					dist[next.to] = newCost;
					pq.offer(new Node(next.to, newCost));
				}
			}
		}
	}
}
