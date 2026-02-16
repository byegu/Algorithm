import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static List<Core> cores;
	static Core[] result;
	static int[][] map;

	static class Core {
		int r;
		int c;
		boolean connect;

		public Core(int r, int c, boolean connect) {
			this.r = r;
			this.c = c;
			this.connect = connect;
		}
	}

	static int state[];
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };

	static int maxCore;
	static int minLen;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			cores = new ArrayList<>();
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp;

					if (temp == 1 && !(i == 0 || j == 0 || i == N - 1 || j == N - 1)) {
						cores.add(new Core(i, j, false));
					}
				}
			}

			state = new int[cores.size()];
			maxCore = -1;
			minLen = Integer.MAX_VALUE;

			dfs(0);
			System.out.println("#" + t + " " + minLen);
		}
	}

	static void simulate() {
		int[][] newMap = new int[N][N];
		int length = 0;
		int coreCnt = 0;

		result = new Core[cores.size()];

		for (int i = 0; i < N; i++)
			newMap[i] = map[i].clone();

		for (int i = 0; i < state.length; i++) {
			if (state[i] == 4) continue;

			Core core = cores.get(i);
			int added = connect(core, state[i], newMap, i);

			if (added == -1) return;

			coreCnt++;
			length += added;
		}

		if (coreCnt > maxCore) {
			maxCore = coreCnt;
			minLen = length;
		} else if (coreCnt == maxCore) {
			minLen = Math.min(minLen, length);
		}
	}

	static int connect(Core core, int dir, int[][] map, int idx) {

		int r = core.r;
		int c = core.c;
		List<int[]> path = new ArrayList<>();

		while (true) {
			r += dr[dir];
			c += dc[dir];

			if (!isIn(r, c)) return -1;

			if (map[r][c] != 0) return -1;

			path.add(new int[] { r, c });

			if (r == 0 || c == 0 || r == N - 1 || c == N - 1) {
				for (int[] p : path)
					map[p[0]][p[1]] = -1;

				result[idx] = new Core(core.r, core.c, true);
				return path.size();
			}
		}
	}

	static void dfs(int depth) {

		int connected = 0;
		for(int i=0;i<depth;i++)
			if(state[i] != 4) connected++;

		if(connected + (cores.size() - depth) < maxCore) return;

		if (depth == cores.size()) {
			simulate();
			return;
		}

		Core core = cores.get(depth);

		for (int d = 0; d < 4; d++) {

			if(!canConnect(core,d)) continue;

			state[depth] = d;
			dfs(depth + 1);
		}

		state[depth] = 4;
		dfs(depth + 1);
	}


	static boolean canConnect(Core core, int dir) {

		int r = core.r;
		int c = core.c;

		while (true) {
			r += dr[dir];
			c += dc[dir];

			if (!isIn(r, c)) return false;

			if (map[r][c] != 0) return false;

			if (r == 0 || c == 0 || r == N - 1 || c == N - 1)
				return true;
		}
	}

	
	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
