import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static int days = 0;
	static int box[][];

	static int M = 0;
	static int N = 0;

	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		days = 0;
		bfs();

		System.out.println(days);
	}

	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 1) {
					q.offer(new Point(i, j));
					visited[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			int size = q.size();
			boolean flag = false;
			for (int idx = 0; idx < size; idx++) {
				Point p = q.poll();
				int pr = p.r;
				int pc = p.c;
				for (int i = 0; i < 4; i++) {
					int nr = pr + dr[i];
					int nc = pc + dc[i];

					if (isIn(nr, nc) && box[nr][nc] != -1 && !visited[nr][nc]) {
						if (box[nr][nc] == 0) {
							visited[nr][nc] = true;
							flag = true;
							box[nr][nc] = 1;
							q.offer(new Point(nr, nc));
						}
					}
				}
			}
			if (flag) {
				days++;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					days = -1;
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M) {
			return true;
		}
		return false;
	}
}
