import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static char[][] map;
	static int N, M;

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int dr[] = { 0, 0, -1, 1 };
	static int dc[] = { -1, 1, 0, 0 };
	static boolean[][] fvisited;
	static boolean[][] jvisited;

	static boolean flag = false;

	static String result = "";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		fvisited = new boolean[N][M];
		jvisited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		result = "";
		flag = false;

		bfs();

		System.out.println(result);

	}

	static void bfs() {

	    Queue<Point> jq = new ArrayDeque<>();
	    Queue<Point> fq = new ArrayDeque<>();

	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < M; j++) {
	            if (map[i][j] == 'F') fq.offer(new Point(i, j));
	            if (map[i][j] == 'J') {
	                jq.offer(new Point(i, j));
	                jvisited[i][j] = true;
	            }
	        }
	    }

	    int time = 0;

	    while (!jq.isEmpty()) {

	        int fSize = fq.size();
	        for (int i = 0; i < fSize; i++) {
	            Point p = fq.poll();
	            for (int j = 0; j < 4; j++) {
	                int nr = p.r + dr[j];
	                int nc = p.c + dc[j];

	                if (isIn(nr, nc) && map[nr][nc] == '.') {
	                    map[nr][nc] = 'F';
	                    fq.offer(new Point(nr, nc));
	                }
	            }
	        }

	        int jSize = jq.size();
	        for (int i = 0; i < jSize; i++) {
	            Point p = jq.poll();

	            for (int j = 0; j < 4; j++) {
	                int nr = p.r + dr[j];
	                int nc = p.c + dc[j];

	                if (!isIn(nr, nc)) {
	                    System.out.println(time + 1);
	                    return;
	                }

	                if (map[nr][nc] == '.' && !jvisited[nr][nc]) {
	                    jvisited[nr][nc] = true;
	                    jq.offer(new Point(nr, nc));
	                }
	            }
	        }

	        time++;
	    }

	    System.out.println("IMPOSSIBLE");
	}
	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}
