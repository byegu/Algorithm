import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 방 번호, 스위치 개수

	static ArrayList<Room>[][] graph;
	static boolean[][] switched;
	static boolean[][] visited;

	static class Room {
		int r;
		int c;

		public Room(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1][N + 1];
		switched = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				graph[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());

			graph[r1][c1].add(new Room(r2, c2));
		}

		bfs();
	}

	static void bfs() {
		Queue<Room> q = new ArrayDeque<Room>();
		q.offer(new Room(1, 1));
		switched[1][1] = true;
		visited[1][1] = true;
		int result = 0;

		while (!q.isEmpty()) {
			Room now = q.poll();
			int r = now.r;
			int c = now.c;

			for (Room next : graph[r][c]) {
				if (!switched[next.r][next.c]) {
					switched[next.r][next.c] = true;

					for (int i = 0; i < 4; i++) {
						int nr = next.r + dr[i];
						int nc = next.c + dc[i];

						if (isIn(nr, nc) && visited[nr][nc]) {
							q.offer(new Room(next.r, next.c));
							visited[next.r][next.c] = true;
							break;
						}
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
			    int nr = r + dr[i];
			    int nc = c + dc[i];

			    if (isIn(nr, nc) && switched[nr][nc] && !visited[nr][nc]) {
			        visited[nr][nc] = true;
			        q.offer(new Room(nr, nc));
			    }
			}

		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (switched[i][j]) {
					result++;
				}
			}
		}

		System.out.println(result);
	}

	static boolean isIn(int r, int c) {
		return r > 0 && r < N + 1 && c > 0 && c < N + 1;
	}
}
