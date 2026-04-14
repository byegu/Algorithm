import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] dist;
	static boolean[][] exist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dist = new int[N + 1][N + 1];
		exist = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
				exist[i][j] = true;
			}
		}
		
		int result = 0;
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j || i == k || j == k) {
						continue;
					}
					
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.println(-1);
						return;
					}
					
					if (dist[i][k] + dist[k][j] == dist[i][j]) {
						exist[i][j] = false;
						exist[j][i] = false;
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (exist[i][j]) {
					result += dist[i][j];
					exist[i][j] = false;
					exist[j][i] = false;
				}
			}
		}
		
		System.out.println(result);
	}
}
