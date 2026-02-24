import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 도시 개수
	static int m; // 버스 개수

	static ArrayList<Node>[] graph;
	static Node[] path;
	static int[] parent;
	static int[] dist;
	static boolean[] visited;

	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static int start;
	static int end;

	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		graph = new ArrayList[n + 1];

		for (int i = 1; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra();
	}

	
    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        dist = new int[n + 1];
        parent = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > dist[now.to]) continue;

            for (Node next : graph[now.to]) {
                int nextCost = dist[now.to] + next.cost;

                if (nextCost < dist[next.to]) {
                    dist[next.to] = nextCost;
                    parent[next.to] = now.to;
                    pq.offer(new Node(next.to, nextCost));
                }
            }
        }

        System.out.println(dist[end]);

        ArrayList<Integer> route = new ArrayList<>();
        int cur = end;

        while (cur != 0) {
            route.add(cur);
            cur = parent[cur];
        }

        System.out.println(route.size());

        for (int i = route.size() - 1; i >= 0; i--) {
            System.out.print(route.get(i) + " ");
        }
    }
}
