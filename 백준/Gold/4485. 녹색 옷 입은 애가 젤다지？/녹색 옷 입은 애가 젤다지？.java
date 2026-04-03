import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] cave;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int idx = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			
			if (N == 0) {
				break;
			}
			
			cave = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("Problem " + idx++ + ": ");
			dijkstra();
		}
		
		System.out.println(sb);
	}

	static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		pq.offer(new int[] { 0, 0, cave[0][0] });
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		dist[0][0] = cave[0][0];

		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int r = now[0];
			int c = now[1];
			int rupee = now[2];

			if (rupee > dist[r][c]) {
				continue;
			}

			if (r == N - 1 && c == N - 1) {
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (!isIn(nr, nc)) {
					continue;
				}

				int nextRupee = rupee + cave[nr][nc];

				if (nextRupee < dist[nr][nc]) {
					dist[nr][nc] = nextRupee;
					pq.offer(new int[] { nr, nc, nextRupee });
				}
			}
		}
		
		sb.append(dist[N-1][N-1] + "\n");
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
