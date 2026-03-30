import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T; // 가로, 세로, 제한 시간
	static int[][] map;
	static boolean[][][] visited;

	static class Point {
		int r;
		int c;
		int dist;
		int gram;

		public Point(int r, int c, int dist, int gram) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.gram = gram;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();
	}

	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;

		int result = -1;
		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;
			int dist = p.dist;
			int gram = p.gram;

			if (r == N - 1 && c == M - 1) {
				result = dist;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				int ngram = gram;

				if (!isIn(nr, nc)) {
					continue;
				}

				if (gram == 0) {
					if (map[nr][nc] == 1) {
						continue;
					}
					
					ngram = (map[nr][nc] == 2) ? 1 : gram;
					
					if (!visited[nr][nc][ngram] && map[nr][nc] == 2) {
						visited[nr][nc][ngram] = true;
						q.offer(new Point(nr, nc, dist + 1, ngram));
					} else if (!visited[nr][nc][ngram] && map[nr][nc] == 0) {
						visited[nr][nc][ngram] = true;
						q.offer(new Point(nr, nc, dist + 1, ngram));
					}
				} else {
					if (!visited[nr][nc][ngram]) {
						visited[nr][nc][ngram] = true;
						q.offer(new Point(nr, nc, dist + 1, ngram));
					}
				}

			}
		}

		if (visited[N-1][M-1][0] || visited[N-1][M-1][1]) {
			if (result <= T) {
				System.out.println(result);
			} else {
				System.out.println("Fail");
			}
		} else {
			System.out.println("Fail");
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
