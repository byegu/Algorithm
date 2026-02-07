import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;

	static int dr[] = { 0, 0, -1, 1 };
	static int dc[] = { 1, -1, 0, 0 };

	static boolean visited[][][];

	static class Point {
		int r;
		int c;
		int dist;
		int breakCnt;

		public Point(int r, int c, int dist, int breakCnt) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.breakCnt = breakCnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, 1, 0));
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;
			int dist = p.dist;
			int breakCnt = p.breakCnt;

			if (r == N - 1 && c == M - 1) {
				return dist;
			}
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!isIn(nr, nc)) {
					continue;
				}

				if (map[nr][nc] == 0) {
					if (!visited[nr][nc][breakCnt]) {
						visited[nr][nc][breakCnt] = true;
						q.offer(new Point(nr, nc, dist + 1, breakCnt));
					}
				} else {
					if (breakCnt == 0 && !visited[nr][nc][1]) {
						visited[nr][nc][1] = true;
						q.offer(new Point(nr, nc, dist + 1, 1));
					}
				}
			}
		}

		return -1;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}