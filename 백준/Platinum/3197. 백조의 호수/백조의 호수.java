import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] lake;

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static Point swan1;
	static Point swan2;
	static Queue<Point> wq = new ArrayDeque<>();
	static Queue<Point> nwq = new ArrayDeque<>();

	static Queue<Point> sq = new ArrayDeque<>();
	static Queue<Point> nsq = new ArrayDeque<>();

	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		lake = new char[R][C];
		visited = new boolean[R][C];

		int swanCount = 0;
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				lake[i][j] = str.charAt(j);

				if (lake[i][j] != 'X') {
					wq.offer(new Point(i, j));
				}
				
				if (lake[i][j] == 'L') {
					if (swanCount == 0) {
						swan1 = new Point(i, j);
						sq.offer(swan1);
					} else {
						swan2 = new Point(i, j);
					}
					swanCount++;
				}
			}
		}
	
		visited[swan1.r][swan1.c] = true;
		
		int days = 0;
		
		while(true) {
			if (move()) {
				System.out.println(days);
				break;
			}
			
			melt();
			
			days++;
		}
	}

	static boolean move() {
		while(!sq.isEmpty()) {
			Point p = sq.poll();
			int r = p.r;
			int c = p.c;
			
			if (r == swan2.r && c == swan2.c) {
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (!isIn(nr, nc)) {
					continue;
				}
				
				if (visited[nr][nc]) {
					continue;
				}
				
				visited[nr][nc] = true;
				
				if (lake[nr][nc] != 'X') {
					sq.offer(new Point(nr, nc));
				} else if (lake[nr][nc] == 'X') {
					nsq.offer(new Point(nr, nc));
				}
			}
		}
		
		sq = nsq;
		nsq = new ArrayDeque<>();
		
		return false;
	}
	
	static void melt() {
		while(!wq.isEmpty()) {
			Point p = wq.poll();
			int r = p.r;
			int c = p.c;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (!isIn(nr, nc)) {
					continue;
				}
				
				if (lake[nr][nc] == 'X') {
					lake[nr][nc] = '.';
					nwq.offer(new Point(nr, nc));
				}
			}
		}
		
		wq = nwq;
		nwq = new ArrayDeque<>();
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
