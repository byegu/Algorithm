import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int size = 16;
	static int start_r = 1;
	static int start_c = 1;
	// 상 하 좌 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean[][] visited = new boolean[size][size];
	static int[][] map = new int[size][size];

	static int flag = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		map = new int[size][size];
		for (int t = 0; t < T; t++) {
			flag = 0;
			visited = new boolean[size][size];
			int test_case = Integer.parseInt(br.readLine());
			for (int i = 0; i < size; i++) {
				String str = br.readLine();
				for (int j = 0; j < size; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			dfs(start_r, start_c);
			System.out.println("#" + test_case + " " + flag);
		}
	}

	static void dfs(int r, int c) {
		if (map[r][c] == 3) {
			flag = 1;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < size && nr >= 0 && nc < size && nc >= 0 && map[nr][nc] != 1 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc);
				visited[nr][nc] = false;
			}
		}
	}
}
