import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static final int N = 12;
	static final int M = 6;
	static char[][] field = new char[N][M];

	static int chain = 0;

	static List<Point> list;
	static boolean[][] visited;

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

	static boolean flag = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				field[i][j] = str.charAt(j);
			}
		}
		while (flag) {
			flag = false;
			traversal();
			drop();
		}
		System.out.println(chain);
	}

	static void bfs(int r, int c) {
		visited = new boolean[N][M];
		list = new ArrayList<>();
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		visited[r][c] = true;

		char cur = field[r][c];
		while (!q.isEmpty()) {
			Point p = q.poll();
			r = p.r;
			c = p.c;
			list.add(p);
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (!isIn(nr, nc)) {
					continue;
				}
				char next = field[nr][nc];

				if (isIn(nr, nc) && !visited[nr][nc] && cur == next) {
					p = new Point(nr, nc);
					visited[nr][nc] = true;
					q.offer(p);
				}
			}
		}

		if (list.size() >= 4) {
			for (Point p : list) {
				field[p.r][p.c] = '.';
			}
			flag = true;
		}
	}

	static void drop() {
		char[][] newField = new char[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newField[i][j] = field[i][j];
				
			}
		}

		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (newField[i][j] != '.' && isIn(i + 1, j) && newField[i + 1][j] == '.') {
					int r = i+1;
					char cur = field[i][j];
					while (isIn(r, j) && newField[r][j] == '.') {
						newField[r][j] = '.';
						r = r + 1;
						if (!isIn(r, j) || newField[r][j] != '.') {
							r--;
							break;
						}
					}
					newField[r][j] = cur;
					newField[i][j] = '.';
				}
			}
		}

		field = newField;
	}

	static void traversal() {
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (field[i][j] != '.') {
					bfs(i, j);
				}
			}
		}
		if (flag) {
			chain++;
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
