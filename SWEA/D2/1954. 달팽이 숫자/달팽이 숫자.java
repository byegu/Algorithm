import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		int dx[] = { 0, 1, 0, -1 };
		int dy[] = { 1, 0, -1, 0 };

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			int snail[][] = new int[N][N];
			int x = 0, y = 0, dir = 0;

			for (int n = 1; n <= N * N; n++) {
				snail[x][y] = n;

				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (nx >= N || nx < 0 || ny >= N || ny < 0 || snail[nx][ny] != 0) {
					dir = (dir + 1) % 4;
					nx = x + dx[dir];
					ny = y + dy[dir];
				}

				x = nx;
				y = ny;
			}
			
			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(snail[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}