import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N, H, W;
	static int[][] map;
	static boolean[][] visited;
	static int[] sel;

	static class Brick {
		int r;
		int c;
		int range;

		public Brick(int r, int c, int range) {
			this.r = r;
			this.c = c;
			this.range = range;
		}
	}

	static int result;

	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			sel = new int[N];
			result = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0);
			System.out.println("#" + t + " " + result);
		}
	}

	static int bfs(int start, int[][] map) {
		Queue<Brick> q = new ArrayDeque<>();
		visited = new boolean[H][W];

		for (int i = 0; i < H; i++) {
			if (map[i][start] != 0) {
				q.offer(new Brick(i, start, map[i][start]));
				visited[i][start] = true;
				map[i][start] = 0;
				break;
			}
		}
		int brick = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Brick b = q.poll();
				int r = b.r;
				int c = b.c;
				int range = b.range;

				for (int i = 0; i < range; i++) {
					for (int dir = 0; dir < 4; dir++) {
						int nr = r + dr[dir] * i;
						int nc = c + dc[dir] * i;
						
						int rr = r + dr[dir] * range;
						int cc = c + dc[dir] * range;
						

						if (!isIn(nr, nc)) {
							continue;
						}

						if (!visited[nr][nc] && map[nr][nc] != 0 && nr == rr || nc == cc) {
							q.offer(new Brick(nr, nc, map[nr][nc]));
							map[nr][nc] = 0;
							visited[nr][nc] = true;
						}
						
						if (!visited[nr][nc] && map[nr][nc] != 0) {
							map[nr][nc] = 0;
							visited[nr][nc] = true;
						}
						
					}
				}
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0) {
					brick++;
				}
			}
		}

		return brick;
	}

	static void gravity(int[][] map) {
	    for (int c = 0; c < W; c++) {
	        int idx = H - 1;

	        for (int r = H - 1; r >= 0; r--) {
	            if (map[r][c] != 0) {
	                int temp = map[r][c];
	                map[r][c] = 0;
	                map[idx][c] = temp;
	                idx--;
	            }
	        }
	    }
	}

	static void dfs(int depth) {
		if (depth == N) {
			int count = Integer.MAX_VALUE;
			int[][] copyMap = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				copyMap[i] = map[i].clone();
			}
			for (int i : sel) {
				count = bfs(i, copyMap);
				gravity(copyMap);
			}

			result = Math.min(result, count);
			return;
		}

		for (int i = 0; i < W; i++) {
			sel[depth] = i;
			dfs(depth + 1);
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
