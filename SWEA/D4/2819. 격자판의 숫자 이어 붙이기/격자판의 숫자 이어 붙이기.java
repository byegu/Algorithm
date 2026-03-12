import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int[][] map;
	static ArrayList<Integer> list;
	static Set<String> set;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static final int N = 4;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			map = new int[N][N];
			visited = new boolean[N][N];
			list = new ArrayList<>();
			set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, 0, "" + map[i][j]);
				}
			}

			System.out.println("#" + t + " " + set.size());
		}
	}

	static void dfs(int r, int c, int depth, String s) {
		if (depth == 6) {
			// 중복 검사 후 리턴
			set.add(s);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (!isIn(nr, nc)) {
				continue;
			}
			dfs(nr, nc, depth + 1, s + map[nr][nc]);
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
