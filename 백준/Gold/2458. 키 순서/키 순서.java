import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 100000000;
	static int N, M;
	static int dist[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			dist[u][v] = 1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][k] != INF && dist[k][j] != INF) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
		}

		int result = 0;

		for (int i = 1; i <= N; i++) {
			int temp = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					continue;
				}

				if (dist[i][j] != INF || dist[j][i] != INF) {
					temp++;
				}
			}
			if (temp == N - 1) {
				result++;
			}
		}

		System.out.println(result);
	}

}
