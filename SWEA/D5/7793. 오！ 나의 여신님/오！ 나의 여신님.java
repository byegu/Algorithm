import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N, M;
	static char map[][];
	static boolean sVisited[][];
	static boolean dVisited[][];

	static class Point {
		int r;
		int c;
		int t;

		public Point(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static boolean flag;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			sVisited = new boolean[N][M];
			dVisited = new boolean[N][M];
			flag = false;

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
				}
			}

			sb.append("#" + t + " ");
			bfs();
		}

		System.out.println(sb);
	}

	static void bfs() {
		Queue<Point> sq = new ArrayDeque<>();
		Queue<Point> dq = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '*') {
					dq.offer(new Point(i, j, 0));
				} else if (map[i][j] == 'S') {
					sq.offer(new Point(i, j, 0));
				}
			}
		}

		while (!sq.isEmpty()) {
			int size = dq.size();
			for (int s = 0; s < size; s++) {
				Point p = dq.poll();
				int r = p.r;
				int c = p.c;

				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (isIn(nr, nc) && !dVisited[nr][nc] && map[nr][nc] != 'D') {
						dq.offer(new Point(nr, nc, 0));
						map[nr][nc] = '*';
						dVisited[nr][nc] = true;
					}
				}
			}

			size = sq.size();
			for (int s = 0; s < size; s++) {
				Point p = sq.poll();
				int r = p.r;
				int c = p.c;
				int t = p.t;

				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (!isIn(nr, nc)) {
						continue;
					}

					if (map[nr][nc] == 'D') {
						sb.append(p.t + 1 + "\n");
						return;
					}

					if (!sVisited[nr][nc] && map[nr][nc] != '*') {
						sq.offer(new Point(nr, nc, t + 1));
						sVisited[nr][nc] = true;
					}
				}
			}
		}

		sb.append("GAME OVER" + "\n");
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 'X';
	}
}
