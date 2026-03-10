import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N; // 섬의 개수

	static class Island {
		int to;
		double cost;

		public Island(int to, double cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static long[] ix;
	static long[] iy;
	static double[][] dist;
	static double E; // 세율

	static boolean[] visited;
	static ArrayList<Island>[] graph;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			graph = new ArrayList[N];
			visited = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();
			sb.append("#" + t + " ");
			
			ix = new long[N];
			iy = new long[N];
			dist = new double[N][N];

			for (int i = 0; i < N; i++) {
				ix[i] = Long.parseLong(st.nextToken());
				graph[i] = new ArrayList<>();

			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				iy[i] = Long.parseLong(st.nextToken());
			}

			E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					long x1 = ix[i];
					long y1 = iy[i];

					long x2 = ix[j];
					long y2 = iy[j];

					dist[i][j] = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
					dist[j][i] = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int from = i;
					int to = j;
					double cost = E * (dist[i][j] * dist[i][j]);

					graph[from].add(new Island(to, cost));
					graph[to].add(new Island(from, cost));
				}
			}

			prim();
			
			System.out.println(sb);
		}
	}

	static void prim() {
		PriorityQueue<Island> pq = new PriorityQueue<>((i1, i2) -> Double.compare(i1.cost, i2.cost));
		pq.offer(new Island(0, 0));
		
		double result = 0;
		while(!pq.isEmpty()) {
			Island now = pq.poll();
			
			if (visited[now.to]) {
				continue;
			}
			
			result += now.cost;
			visited[now.to] = true;
			
			for (Island next : graph[now.to]) {
				if (!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		sb.append(Math.round(result));
	}

}
