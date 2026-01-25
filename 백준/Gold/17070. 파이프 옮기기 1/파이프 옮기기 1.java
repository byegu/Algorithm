import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] house;
	static long[][][] dp;

	static int[] dr = { 0, 1, 1 };
	static int[] dc = { 1, 0, 1 };
	// 현재 상태에서 이동 가능한 방향 체크
	static boolean[][] canMove = { { true, false, true }, // 가로
			{ false, true, true }, // 세로
			{ true, true, true } // 대각
	};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		house = new int[N][N];
		dp = new long[N][N][3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(dfs(0, 1, 0));
	}

	static long dfs(int r, int c, int state) {
		if (r == N - 1 && c == N - 1) {
			return 1;
		}

		if (dp[r][c][state] != -1) {
			return dp[r][c][state];
		}

		long count = 0;

		for (int dir = 0; dir < 3; dir++) {
			// 현재 상태에서 이동 불가능한 방향이면 continue
			if (!canMove[state][dir]) {
				continue;
			}

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			// 파이프가 집 밖으로 넘어가면 continue
			if (nr >= N || nc >= N) {
				continue;
			}

			// 이동 방향에 벽이 있는지 확인
			if (dir == 2) { // 대각 상태일 때
				if (house[r][c + 1] == 1) { // 가로에 벽이 있는 경우
					continue;
				} else if (house[r + 1][c] == 1) { // 세로에 벽이 있는 경우
					continue;
				} else if (house[nr][nc] == 1) { // 대각에 벽이 있는 경우
					continue;
				}
			} else { // 가로 혹은 세로 상태일 때
				if (house[nr][nc] == 1) { // 진행 방향이 벽이 있는 경우
					continue;
				}
			}

			count += dfs(nr, nc, dir);
		}

		dp[r][c][state] = count; // 도착 방법 수 저장

		return count;
	}
}
