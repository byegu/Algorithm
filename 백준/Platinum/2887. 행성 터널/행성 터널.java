import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static int[][] planets;
	static ArrayList<Planet>[] graph;
	static boolean[] visited;

	static class Planet {
		int to;
		int cost;

		public Planet(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		planets = new int[N][4];
		graph = new ArrayList[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			planets[i][0] = Integer.parseInt(st.nextToken());
			planets[i][1] = Integer.parseInt(st.nextToken());
			planets[i][2] = Integer.parseInt(st.nextToken());
			planets[i][3] = i;
		}
		
		Arrays.sort(planets, (a, b) -> Integer.compare(a[0], b[0]));
		
		for (int i = 0; i < N - 1; i++) {
			int from = planets[i][3];
			int to = planets[i + 1][3];

			int cost = Math.abs(planets[i][0] - planets[i + 1][0]);

			graph[from].add(new Planet(to, cost));
			graph[to].add(new Planet(from, cost));
		}
		
		Arrays.sort(planets, (a, b) -> Integer.compare(a[1], b[1]));
		
		for (int i = 0; i < N - 1; i++) {
			int from = planets[i][3];
			int to = planets[i + 1][3];

			int cost = Math.abs(planets[i][1] - planets[i + 1][1]);

			graph[from].add(new Planet(to, cost));
			graph[to].add(new Planet(from, cost));
		}
		
		Arrays.sort(planets, (a, b) -> Integer.compare(a[2], b[2]));

		for (int i = 0; i < N - 1; i++) {
			int from = planets[i][3];
			int to = planets[i + 1][3];

			int cost = Math.abs(planets[i][2] - planets[i + 1][2]);

			graph[from].add(new Planet(to, cost));
			graph[to].add(new Planet(from, cost));
		}

		prim();
	}

	static void prim() {
		PriorityQueue<Planet> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		pq.offer(new Planet(0, 0));
		int result = 0;
		int[] dist = new int[N];

		for (int i = 0; i < N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[0] = 0;

		while (!pq.isEmpty()) {
			Planet now = pq.poll();

			if (visited[now.to]) {
				continue;
			}

			visited[now.to] = true;
			result += now.cost;

			for (Planet next : graph[now.to]) {
				if (!visited[next.to] && next.cost < dist[next.to]) {
					dist[next.to] = next.cost;
					pq.offer(next);
				}
			}
		}
		System.out.println(result);
	}

}
