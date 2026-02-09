import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int map[][];
	static int N;

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static boolean[][] visited;

	static int room = Integer.MAX_VALUE;
	static int count = 0;

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

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			room = Integer.MAX_VALUE;
			count = 0;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			System.out.println("#" + t + " " + room + " " + count);
		}
	}

	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		for (int n = 1; n <= N*N; n++) {
			visited = new boolean[N][N];
			int r = 0;
			int c = 0;
			int startRoom = n;
			int roomCnt = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == n) {
						r = i;
						c = j;
					}
				}
			}
			q.offer(new Point(r, c));
			while (!q.isEmpty()) {
				Point p = q.poll();
				r = p.r;
				c = p.c;
				visited[r][c] = true;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (isIn(nr, nc) && !visited[nr][nc] && map[r][c] == map[nr][nc] - 1) {
						visited[nr][nc] = true;
						roomCnt += 1;
						q.offer(new Point(nr, nc));
					}
				}
			}
			if (count == roomCnt) {
				room = Math.min(startRoom, room); 
			} else if (count < roomCnt) {
				count = roomCnt;
				room = startRoom;
			}
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
