import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Climbing {
	static int N, M;
	static int min;
	static int map[][];

	static int dr[] = { 0, 0, -1, 1 };
	static int dc[] = { -1, 1, 0, 0 };

	static boolean[][] visited;
	static int level = 1;
	static boolean check = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			min = Integer.MAX_VALUE;
			check = false;
			level = 1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 1; i < N; i++) {
				level = i;
				visited = new boolean[N][M];
				visited[N - 1][0] = true;
				dfs(N - 1, 0);
				if (check) {
					System.out.println("#" + t + " " + level);
					break;
				}
			}
		}
	}

	static void dfs(int r, int c) {
		if (map[r][c] == 3) {
			check = true;
			return;
		}

		for (int i = 0; i < 4; i++) {
			for (int times = 1; times <= level; times++) {
				int nr = r + dr[i] * times;
				int nc = c + dc[i];
				if (isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					dfs(nr, nc);
				}
			}
		}

	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 0;
	}
}
