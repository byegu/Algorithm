import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;

	static int map[][];

	static class Cctv {
		int r;
		int c;
		int type;

		public Cctv(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}

	static ArrayList<Cctv> list = new ArrayList<>();
	static int[] state = new int[9];

	static int blindSpot = Integer.MAX_VALUE;

	// 오른쪽 아래 왼쪽 위
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };

	// 각 cctv 별 퍼지는 방향
	static int[][] dir = { {}, { 0 }, { 0, 2 }, { 3, 0 }, { 2, 0, 3 }, { 2, 3, 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 6 && map[i][j] > 0) {
					list.add(new Cctv(i, j, map[i][j]));
				}
			}
		}

		dfs(0);
		System.out.println(blindSpot);
	}

	static void simulate() {

		int[][] monitor = new int[N][M];
		int count = 0;
		for (int i = 0; i < N; i++) {
			monitor[i] = map[i].clone();
		}

		for (int i = 0; i < list.size(); i++) {
			Cctv c = list.get(i);
			int curDir = state[i];

			for (int j = 0; j < dir[c.type].length; j++) {
				int d = (dir[c.type][j] + curDir) % 4;

				watch(c.r, c.c, d, monitor);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (monitor[i][j] == 0) {
					count++;
				}
			}
		}

		blindSpot = Math.min(count, blindSpot);
	}

	static void watch(int r, int c, int d, int[][] map) {
		while (true) {
			r += dr[d];
			c += dc[d];

			if (!isIn(r, c))
				break;
			if (map[r][c] == 6)
				break;

			if (map[r][c] == 0)
				map[r][c] = -1;
		}
	}

	static void dfs(int depth) {
		if (depth == list.size()) {
			simulate();
			return;
		}

		for (int d = 0; d < 4; d++) {
			state[depth] = d;
			dfs(depth + 1);
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
