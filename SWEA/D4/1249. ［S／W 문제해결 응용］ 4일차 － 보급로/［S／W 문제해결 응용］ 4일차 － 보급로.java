import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
	static int N = 0;
	static int map[][];

	// 오른쪽 아래 왼쪽 위
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}
			
			System.out.println("#" + t + " " + dijkstra());
		}
	}

	static int dijkstra() {
		Queue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		q.offer(new int[] {0, 0, 0});
		int[][] dist = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		dist[0][0] = 0;
		
		while (!q.isEmpty()) {
			int[] p = q.poll();
			
			if (p[0] == N -1 && p[1] == N-1) {
				return p[2];
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = p[0] + dr[i];
				int nc = p[1] + dc[i];
				
				if (!isIn(nr, nc)) continue;
				
				if (dist[nr][nc] > p[2] + map[nr][nc]) {
					dist[nr][nc] = p[2] + map[nr][nc];
					q.offer(new int[] {nr, nc, dist[nr][nc]});
				}
			}
		}
		
		return -1;
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
