import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// ���μ��� �����ϱ�
public class swea_1767 {
	static int N;
	static List<Core> cores;

	static int[][] map;

	static class Core {
		int r;
		int c;

		public Core(int r, int c) {
			this.r = r;
			this.c = c;
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
						cores.add(new Core(i, j));
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
		int length = 0;
		int coreCnt = 0;

		List<int[]> wires = new ArrayList<>();

		for (int i = 0; i < state.length; i++) {
			if (state[i] == 4)
				continue;

			Core core = cores.get(i);
			int added = connect(core, state[i], wires);

			if (added == -1) {
				for (int[] w : wires) {
					map[w[0]][w[1]] = 0;
				}
				return;
			}

			coreCnt++;
			length += added;
		}

		if (coreCnt > maxCore) {
			maxCore = coreCnt;
			minLen = length;
		} else if (coreCnt == maxCore) {
			minLen = Math.min(minLen, length);
		}
		
		for (int[] w : wires) {
			map[w[0]][w[1]] = 0;
		}
	}

	static int connect(Core core, int dir, List<int[]> wires) {
		int r = core.r;
		int c = core.c;
		List<int[]> path = new ArrayList<>();

		while (true) {
			r += dr[dir];
			c += dc[dir];

			if (!isIn(r, c))
				return -1;

			if (map[r][c] != 0)
				return -1;

			path.add(new int[] { r, c });

			if (r == 0 || c == 0 || r == N - 1 || c == N - 1) {
				for (int[] p : path) {
					map[p[0]][p[1]] = -1;
					wires.add(p);
				}

				return path.size();
			}
		}
	}

	static void dfs(int depth) {
		int connected = 0;

		for (int i = 0; i < depth; i++)
			if (state[i] != 4)
				connected++;

		// 현재 연결된 프로세서 + 남은 코어 수 < 최고 기록이면
		// 남은 코어를 모두 연결해도 의미가 없기 때문에 return
		if (connected + (cores.size() - depth) < maxCore)
			return;

		if (depth == cores.size()) {
			// 모든 코어에 대해 방향을 정했다면 simulate
			simulate();
			return;
		}

		Core core = cores.get(depth);

		for (int d = 0; d < 4; d++) {

			if (!canConnect(core, d))
				continue;

			// 연결 가능하면 프로세서 상태 연결 방향 저장
			state[depth] = d;
			dfs(depth + 1);
		}

		// 현재 프로세서를 연결하지 않고 다음 프로세서를 연결해보는 경우를 보기 위해 + 백트래킹
		state[depth] = 4;
		dfs(depth + 1);
	}

	static boolean canConnect(Core core, int dir) {
		int r = core.r;
		int c = core.c;

		while (true) {
			r += dr[dir];
			c += dc[dir];

			if (!isIn(r, c))
				return false;

			if (map[r][c] != 0)
				return false;

			if (r == 0 || c == 0 || r == N - 1 || c == N - 1)
				return true;
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
