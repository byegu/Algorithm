import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int H, W, N; // 세로, 가로, 치즈 개수
	static char[][] map;
	static boolean[][][] visited;
	
	static class Point {
		int r;
		int c;
		int dist;
		int health;
		public Point(int r, int c, int dist, int health) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.health = health;
		}
	}
	
	static Point start;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		visited = new boolean[H][W][N+2];
		
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == 'S') {
					start = new Point(i, j, 0, 1);
				}
			}
		}
		
		bfs();
	}

	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.r][start.c][0] = true;
		
		int result = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;
			int dist = p.dist;
			int health = p.health;
			
			if (health == N+1) {
				result = dist;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (!isIn(nr, nc) || map[nr][nc] == 'X') {
					continue;
				}
				
				int cheese = map[nr][nc] - '0';
				
				if (!visited[nr][nc][health] && map[nr][nc] == '.') {
					q.offer(new Point(nr, nc, dist+1, health));
					visited[nr][nc][health] = true;
				} else if(!visited[nr][nc][health] && cheese == health) {
					q.offer(new Point(nr, nc, dist+1, health+1));
					visited[nr][nc][health+1] = true;
				} else if(!visited[nr][nc][health] && map[nr][nc] != '.') {
					q.offer(new Point(nr, nc, dist+1, health));
					visited[nr][nc][health] = true;
				}
			}
		}
		
		System.out.println(result);
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
