import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int map[][];
	static boolean visited[][];
	static int count;

	static int dr[] = { 0, 0, -1, 1 };
	static int dc[] = { -1, 1, 0, 0 };
	
	static ArrayList<Island>[] graph;
	
	static class Island {
		int to;
		int cost;
		
		public Island(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		count = 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		traversal();
		prim();
	}
	
	static void prim() {
		PriorityQueue<Island> pq = new PriorityQueue<>((i1, i2) -> i1.cost - i2.cost);
		pq.offer(new Island(1, 0));
		boolean[] visited2 = new boolean[count];
		int result = 0;
		
		while(!pq.isEmpty()) {
			Island now = pq.poll();
			
			if(visited2[now.to]) {
				continue;
			}
			
			visited2[now.to] = true;
			result += now.cost;
			
			for (Island next : graph[now.to]) {
				if (!visited2[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		for (int i = 1; i < visited2.length; i++) {
			if(!visited2[i]) {
				result = -1;
			}
		}
		
		System.out.println(result);
	}

	static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;
		map[r][c] = count;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			r = now[0];
			c = now[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
					map[nr][nc] = count;
				}
			}
		}
		
		count++;
	}
	
	static void bridge(int r, int c, int from) {
		for (int i = 0; i < 4; i++) {
			connect(r, c, i, from);
		}
	}
	
	static void connect(int r, int c, int dir, int from) {
		List<Point> path = new ArrayList<>();
		int cost = 0;
		
		int nr = r;
		int nc = c;
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			if (!isIn(nr, nc)) {
				return;
			}
			
			if (map[nr][nc] == from) {
				return;
			}
			
			path.add(new Point(nr, nc));
			cost++;
			
			if (map[nr][nc] != 0 && map[nr][nc] != -1 && cost <= 2) {
				return;
			}
			
			if (map[nr][nc] != 0 && map[nr][nc] != -1 && cost > 2) {
				int to = map[nr][nc];
				for (Point p : path) {
					if (map[p.r][p.c] == 0 || map[p.r][p.c] == -1) {
						map[p.r][p.c] = -1;						
					}
				}
				cost--;
				graph[from].add(new Island(to, cost));
				graph[to].add(new Island(from, cost));
				
				return;
			}
		}
	}

	static void traversal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		graph = new ArrayList[count];
		visited = new boolean[N][M];
		
		for (int i = 1; i < count; i++) {
			graph[i] = new ArrayList<>();
		}
				
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) {
					bridge(i, j, map[i][j]);
				}
			}
		}
	}
	

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
