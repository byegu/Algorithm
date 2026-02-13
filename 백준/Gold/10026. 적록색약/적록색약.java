import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N = 0;
	static char[][] generalMap;
	static char[][] blindMap;
	static boolean[][] visited;

	static int general;
	static int blindness;
	static int flag = 0;

	// 오른쪽 아래 왼쪽 위
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

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

		N = Integer.parseInt(br.readLine());
		generalMap = new char[N][N];
		blindMap = new char[N][N];
		visited = new boolean[N][N];
		general = 0;
		blindness = 0;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				generalMap[i][j] = str.charAt(j);
				if (str.charAt(j) == 'G') {
					blindMap[i][j] = 'R';
				} else {
					blindMap[i][j] = str.charAt(j);
				}
			}
		}

		traversal(generalMap);
		visited = new boolean[N][N];
		flag = 1;
		traversal(blindMap);

		System.out.println(general + " " + blindness);
	}

	static void bfs(int r, int c, char[][] map) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		visited[r][c] = true;
		int count = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();
			r = p.r;
			c = p.c;
			char cur = map[r][c];
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (!isIn(nr, nc))
					continue;

				char next = map[nr][nc];

				if (isIn(nr, nc) && !visited[nr][nc] && cur == next) {
					q.offer(new Point(nr, nc));
					visited[nr][nc] = true;
					count++;
				}
			}
		}
		if (count > 0 && flag != 1) {
			general++;
		}
		if (count > 0 && flag == 1) {
			blindness++;
		}
	}

	static void traversal(char[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, map);
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
