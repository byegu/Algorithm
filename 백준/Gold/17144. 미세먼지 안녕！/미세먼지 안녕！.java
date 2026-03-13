import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T; // row, column, time
	static int map[][];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static ArrayList<Purifier> list;

	static class Purifier {
		int r;
		int c;

		public Purifier(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		list = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == -1) {
					list.add(new Purifier(i, j));
				}
			}
		}

		simulate();
	}

	static void simulate() {

		for (int t = 0; t < T; t++) {
			int[][] temp = new int[R][C];
			for (Purifier p : list) {
				temp[p.r][p.c] = -1;
			}

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] > 0) {
						int spread = map[r][c] / 5; // 퍼지는 양
						int count = 0; // 퍼진 횟수
						
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];

							if (!isIn(nr, nc) || map[nr][nc] == -1) {
								continue;
							}

							temp[nr][nc] += spread;
							count++;
						}
						temp[r][c] += map[r][c] - (spread * count);
					}
				}
			}
			map = temp;
			rotate();
		}

		int result = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					result += map[i][j];
				}
			}
		}

		System.out.println(result);
	}

	static void rotate() {
		int top = list.get(0).r;
		int bottom = list.get(1).r;

		// 위
		for (int i = top - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}

		// 왼
		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}

		// 아래
		for (int i = 0; i < top; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}

		// 오른
		for (int i = C - 1; i > 1; i--) {
			map[top][i] = map[top][i - 1];
		}

		map[top][1] = 0;

		// 아래
		for (int i = bottom + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}

		// 왼
		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}

		// 위
		for (int i = R - 1; i > bottom; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}

		// 오른
		for (int i = C - 1; i > 1; i--) {
			map[bottom][i] = map[bottom][i - 1];
		}

		map[bottom][1] = 0;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
