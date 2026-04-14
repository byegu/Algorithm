import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 100000000;
	static int V, E; // 마을의 수, 도로의 수
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[V + 1][V + 1];

		for (int i = 1; i <= V; i++) {
			Arrays.fill(dist[i], INF);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			dist[u][v] = Math.min(dist[u][v], w);
		}

		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (dist[i][k] != INF && dist[k][j] != INF) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
		}

		int result = INF;

		for (int i = 1; i <= V; i++) {
			result = Math.min(result, dist[i][i]);
		}

		if (result == INF) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}
}
