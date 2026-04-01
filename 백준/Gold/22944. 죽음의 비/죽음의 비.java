import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, H, D; // 한 변의 길이, 현재 체력, 우산 내구도
	static char[][] map;
	static int[][] visited;

	static class Point {
		int r;
		int c;
		int health;
		int umbrella;
		int dist;

		public Point(int r, int c, int health, int umbrella, int dist) {
			this.r = r;
			this.c = c;
			this.health = health;
			this.umbrella = umbrella;
			this.dist = dist;
		}

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	static Point start;
	static Point end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new char[N][N];
		visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == 'S') {
					start = new Point(i, j, H, 0, 0);
				} else if (map[i][j] == 'E') {
					end = new Point(i, j);
				}
			}
		}

		bfs();
	}

	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.r][start.c] = H;
		int result = 0;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;
			int h = p.health;
			int u = p.umbrella;
			int dist = p.dist;

			if (h == 0) {
				result = -1;
			} else if (h > 0 && r == end.r && c == end.c) {
				result = dist;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				int nu = u;

				if (!isIn(nr, nc)) {
					continue;
				}

				nu = (map[nr][nc] == 'U') ? D - 1 : u;
				
				if (visited[nr][nc] >= h + nu) continue;

				if (map[nr][nc] == '.' && nu > 0) {
					visited[nr][nc] = h + nu;
					q.offer(new Point(nr, nc, h, nu - 1, dist + 1));
				} else if (map[nr][nc] == '.' && nu == 0) {
					visited[nr][nc] = h + nu;
					q.offer(new Point(nr, nc, h - 1, nu, dist + 1));
				} else if ( map[nr][nc] == 'U') {
					visited[nr][nc] = h + nu;
					q.offer(new Point(nr, nc, h, nu, dist + 1));
				} else if (map[nr][nc] == 'E' && nu == 0) {
					visited[nr][nc] = h + nu;
					q.offer(new Point(nr, nc, h, nu, dist + 1));
				} else if (map[nr][nc] == 'E' && nu > 0) {
					visited[nr][nc] = h + nu;
					q.offer(new Point(nr, nc, h, nu, dist + 1));
				}
			}
		}
		
		System.out.println(result);
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
