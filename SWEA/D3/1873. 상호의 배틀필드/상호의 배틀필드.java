import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int H = sc.nextInt();
			int W = sc.nextInt();

			char field[][] = new char[H][W];

			int tank_r = 0, tank_c = 0;

			for (int i = 0; i < H; i++) {
				String s = sc.next();
				for (int j = 0; j < W; j++) {
					field[i][j] = s.charAt(j);
					if (field[i][j] == '^' || field[i][j] == 'v' || field[i][j] == '<' || field[i][j] == '>') {
						tank_r = i;
						tank_c = j;
					}
				}
			}

			int N = sc.nextInt();

			String cmd = sc.next();
			char command[] = new char[N];
			for (int i = 0; i < N; i++) {
				command[i] = cmd.charAt(i);
			}
			// 위 아래 오른쪽 왼쪽
			int dr[] = { -1, 1, 0, 0 };
			int dc[] = { 0, 0, 1, -1 };

			for (int i = 0; i < N; i++) {
				int nr = 0, nc = 0;
				if (command[i] == 'U') {
					nr = tank_r + dr[0];
					nc = tank_c + dc[0];
					if (nr >= H || nr < 0 || nc >= W || nc < 0) {
						field[tank_r][tank_c] = '^';
					} else if (field[nr][nc] != '.') {
						field[tank_r][tank_c] = '^';
					} else {
						field[tank_r][tank_c] = '.';
						field[nr][nc] = '^';
						tank_r = nr;
						tank_c = nc;
					}
				} else if (command[i] == 'D') {
					nr = tank_r + dr[1];
					nc = tank_c + dc[1];
					if (nr >= H || nr < 0 || nc >= W || nc < 0) {
						field[tank_r][tank_c] = 'v';
					} else if (field[nr][nc] != '.') {
						field[tank_r][tank_c] = 'v';
					} else {
						field[tank_r][tank_c] = '.';
						field[nr][nc] = 'v';
						tank_r = nr;
						tank_c = nc;
					}
				} else if (command[i] == 'R') {
					nr = tank_r + dr[2];
					nc = tank_c + dc[2];
					if (nr >= H || nr < 0 || nc >= W || nc < 0) {
						field[tank_r][tank_c] = '>';
					} else if (field[nr][nc] != '.') {
						field[tank_r][tank_c] = '>';
					} else {
						field[tank_r][tank_c] = '.';
						field[nr][nc] = '>';
						tank_r = nr;
						tank_c = nc;
					}
				} else if (command[i] == 'L') {
					nr = tank_r + dr[3];
					nc = tank_c + dc[3];
					if (nr >= H || nr < 0 || nc >= W || nc < 0) {
						field[tank_r][tank_c] = '<';
					} else if (field[nr][nc] != '.') {
						field[tank_r][tank_c] = '<';
					} else {
						field[tank_r][tank_c] = '.';
						field[nr][nc] = '<';
						tank_r = nr;
						tank_c = nc;
					}
				} else if (command[i] == 'S') {
					int shr = tank_r, shc = tank_c;
					if (field[tank_r][tank_c] == '^') {
						shr += dr[0];
						shc += dc[0];
						while (true) {
							if (shr >= H || shr < 0 || shc >= W || shc < 0) {
								break;
							}
							if (field[shr][shc] == '#') {
								break;
							} else if (field[shr][shc] == '*') {
								field[shr][shc] = '.';
								break;
							}
							shr += dr[0];
							shc += dc[0];
						}
					} else if (field[tank_r][tank_c] == 'v') {
						shr += dr[1];
						shc += dc[1];
						while (true) {
							if (shr >= H || shr < 0 || shc >= W || shc < 0) {
								break;
							}
							if (field[shr][shc] == '#') {
								break;
							} else if (field[shr][shc] == '*') {
								field[shr][shc] = '.';
								break;
							}
							shr += dr[1];
							shc += dc[1];
						}
					} else if (field[tank_r][tank_c] == '>') {
						shr += dr[2];
						shc += dc[2];
						while (true) {
							if (shr >= H || shr < 0 || shc >= W || shc < 0) {
								break;
							}
							if (field[shr][shc] == '#') {
								break;
							} else if (field[shr][shc] == '*') {
								field[shr][shc] = '.';
								break;
							}
							shr += dr[2];
							shc += dc[2];
						}
					} else if (field[tank_r][tank_c] == '<') {
						shr += dr[3];
						shc += dc[3];
						while (true) {
							if (shr >= H || shr < 0 || shc >= W || shc < 0) {
								break;
							}
							if (field[shr][shc] == '#') {
								break;
							} else if (field[shr][shc] == '*') {
								field[shr][shc] = '.';
								break;
							}
							shr += dr[3];
							shc += dc[3];
						}
					}
				}
			}
			System.out.print("#" + t + " ");
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					System.out.print(field[h][w]);
				}
				System.out.println();
			}
		}

	}
}