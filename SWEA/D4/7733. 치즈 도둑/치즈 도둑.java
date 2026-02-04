import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static int max = 0;
	static int count = 0;
	static int temp = 0;

	static int N = 0;
	static boolean[][] visited;

	static class Point {
		int r;
		int c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			int cheese[][] = new int[N][N];
			max = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int day = 0; day <= 100; day++) {

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (cheese[i][j] == day) {
							cheese[i][j] = -1;
						}
					}
				}
				count = 0;
				visited = new boolean[N][N];
				traversal(cheese);
				max = Math.max(max, count);
			}

			System.out.println("#" + t + " " + max);
		}

	}

	static void bfs(int[][] cheese, int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			r = p.r;
			c = p.c;
			for (int i = 0; i < 4; i++) {
				if (!visited[r][c]) {
					visited[r][c] = true;
					temp++;
				}
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (isIn(nr, nc) && !visited[nr][nc] && cheese[nr][nc] != -1) {
					visited[nr][nc] = true;
					q.offer(new Point(nr, nc));
					temp++;
				}
			}
		}

		if (temp > 0) {
			count++;
		}

		temp = 0;
	}

	static void traversal(int[][] cheese) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cheese[i][j] != -1) {
					bfs(cheese, i, j);
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}
}
