import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N = 0;
	static int M = 0;

	static int[][] map;

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static boolean[][] visited;

	static int max = 0;

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
		System.out.println(max);
	}

	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		visited = new boolean[N][M];

		int virus[][] = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			virus[i] = map[i].clone();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					q.offer(new Point(i, j));
					visited[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= N || nr < 0 || nc >= M || nc < 0) {
					continue;
				}
				if (visited[nr][nc]) {
					continue;
				}
				if (virus[nr][nc] == 0) {
					visited[nr][nc] = true;
					virus[nr][nc] = 2;
					q.offer(new Point(nr, nc));
				}
			}
		}

		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (virus[i][j] == 0) {
					count++;
				}
			}
		}

		max = Math.max(count, max);

	}

	static void dfs(int wall, int start) {
		if (wall == 3) {
			bfs();
			return;
		}

		for (int i = start; i <= N * M; i++) {
			int r = i / M;
			int c = i % M;
			if (isIn(r, c) && map[r][c] == 0) {
				map[r][c] = 1;
				dfs(wall + 1, i + 1);
				map[r][c] = 0;
			}
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
