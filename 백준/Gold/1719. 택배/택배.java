import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 1000000000;
	static int n, m; // 집하장 개수, 경로 개수
	static int dist[][];
	static int route[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dist = new int[n + 1][n + 1];
		route = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			dist[u][v] = Math.min(dist[u][v], w);
			dist[v][u] = Math.min(dist[v][u], w);
		}
		
		for (int i = 1; i <= n; i++) {
		    for (int j = 1; j <= n; j++) {
		        if (i != j && dist[i][j] != INF) {
		            route[i][j] = j;
		        }
		    }
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (dist[i][k] != INF && dist[k][j] != INF) {
						if (dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
							route[i][j] = route[i][k];
						}
					}
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					System.out.print("- ");
					continue;
				}
				System.out.print(route[i][j] + " ");
			}
			System.out.println();
		}
	}
}
