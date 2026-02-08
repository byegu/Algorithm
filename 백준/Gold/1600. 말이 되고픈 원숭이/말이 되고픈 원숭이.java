import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int W;
	static int H;
	static int K;
	static int[][] map;

	static int[][][] visited;

	// 오른쪽 아래 왼쪽 위
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	// 오른쪽 위부터 시계 방향 -말(Horse) 움직임 범위
	static int[] horseDr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] horseDc = { 1, 2, 2, 1, -1, -2, -2, -1 };

	static class Point {
		int r;
		int c;
		int horseCnt;
		int moveCnt;

		public Point(int r, int c, int horseCnt, int moveCnt) {
			this.r = r;
			this.c = c;
			this.horseCnt = horseCnt;
			this.moveCnt = moveCnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		visited = new int[H][W][K+1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs());

	}

	static int bfs() {
	    Queue<Integer> q = new ArrayDeque<>();

	    int start = ((0 * W + 0) * (K+1)) + 0;

	    q.offer(start);
	    visited[0][0][0] = 1;

	    while(!q.isEmpty()) {

	        int cur = q.poll();

	        int horse = cur % (K+1);
	        cur /= (K+1);

	        int c = cur % W;
	        int r = cur / W;

	        int move = visited[r][c][horse];

	        if(r == H-1 && c == W-1) {
	            return move - 1;
	        }

	        for(int i=0;i<12;i++) {

	            int nr, nc, nh = horse;

	            if(i < 4) {
	                nr = r + dr[i];
	                nc = c + dc[i];
	            } else {
	                nr = r + horseDr[i-4];
	                nc = c + horseDc[i-4];
	                nh++;
	            }

	            if(!isIn(nr,nc)) continue;
	            if(map[nr][nc] == 1) continue;
	            if(nh > K) continue;
	            if(visited[nr][nc][nh] != 0) continue;

	            visited[nr][nc][nh] = move + 1;

	            int next = ((nr * W + nc) * (K+1)) + nh;

	            q.offer(next);
	        }
	    }

		return -1;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
