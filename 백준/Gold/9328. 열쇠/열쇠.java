import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int H, W;
	static char[][] map;
	static boolean[][] visited;

	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	static ArrayList<int[]> entrance;

	static boolean[] keys;
	static ArrayList<Door>[] doors;
	static int document;

	static class Door {
		int r;
		int c;

		public Door(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			entrance = new ArrayList<>();
			document = 0;
			keys = new boolean[27];
			doors = new ArrayList[27];

			for (int i = 0; i < doors.length; i++) {
				doors[i] = new ArrayList<>();
			}

			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					char ch = map[i][j];

					if ((i == 0 || j == 0 || i == H - 1 || j == W - 1) && ch >= 'a' && ch <= 'z') {
						int key = ch - 'a';
						keys[key] = true;
						map[i][j] = '.';
					} else if (ch >= 'A' && ch <= 'Z') {
						int door = ch - 'A';
						doors[door].add(new Door(i, j));
					}
				}
			}

			String str = br.readLine();
			if (!str.equals("0")) {
				for (int i = 0; i < str.length(); i++) {
					int key = str.charAt(i) - 'a';

					keys[key] = true;
				}
			}

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					char ch = map[i][j];
					if ((i == 0 || j == 0 || i == H - 1 || j == W - 1) && ch >= 'A' && ch <= 'Z') {
						int door = ch - 'A';

						if (keys[door]) {
							entrance.add(new int[] { i, j });
						}
					} else if ((i == 0 || j == 0 || i == H - 1 || j == W - 1) && ch >= 'a' && ch <= 'z') {
						int key = ch - 'a';
						keys[key] = true;
						map[i][j] = '.';
						entrance.add(new int[] { i, j });
					} else if ((i == 0 || j == 0 || i == H - 1 || j == W - 1) && map[i][j] == '.') {
						entrance.add(new int[] { i, j });
					} else if ((i == 0 || j == 0 || i == H - 1 || j == W - 1) && map[i][j] == '$') {
						document++;
						map[i][j] = '.';
						entrance.add(new int[] { i, j });
					}

				}
			}

			if (entrance.size() == 0) {
				document = 0;
			} else {
				bfs();
			}
			System.out.println(document);
		}

	}

	static void bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();

		for (int i = 0; i < entrance.size(); i++) {
			visited = new boolean[H][W];
			int r = entrance.get(i)[0];
			int c = entrance.get(i)[1];
			q.offer(new int[] { r, c });
			visited[r][c] = true;
		}

		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int r = arr[0];
			int c = arr[1];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (isIn(nr, nc) && !visited[nr][nc]) {
					char ch = map[nr][nc];
					if (ch == '.') {
						q.offer(new int[] { nr, nc });
						visited[nr][nc] = true;
					} else if (ch >= 'a' && ch <= 'z') {
						int key = ch - 'a';
						keys[key] = true;
						q.offer(new int[] { nr, nc });
						for (Door door : doors[key]) {
							int doorR = door.r;
							int doorC = door.c;

							if (canVisit(doorR, doorC)) {
								q.offer(new int[] { doorR, doorC });
								visited[doorR][doorC] = true;
							}
						}

						visited[nr][nc] = true;
						map[nr][nc] = '.';
					} else if (ch >= 'A' && ch <= 'Z') {
						int door = ch - 'A';
						if (keys[door]) {
							q.offer(new int[] { nr, nc });
							visited[nr][nc] = true;
							map[nr][nc] = '.';
						}
					} else if (ch == '$') {
						q.offer(new int[] { nr, nc });
						document++;
						visited[nr][nc] = true;
						map[nr][nc] = '.';
					}
				}
			}
		}
	}

	static boolean canVisit(int r, int c) {
		if (r == 0 || c == 0 || r == H - 1 || c == W - 1) {
			return true;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (isIn(nr, nc) && visited[nr][nc]) {
				return true;
			}

		}

		return false;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W && map[r][c] != '*';
	}

}
