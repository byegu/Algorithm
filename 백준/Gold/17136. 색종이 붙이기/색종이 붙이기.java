import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N = 10;
	static int[][] map = new int[N][N];
	static ArrayList<int[]> list = new ArrayList<>();

	static int paper = Integer.MAX_VALUE;

	static int[] paperCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		paperCount = new int[] { 5, 5, 5, 5, 5 };
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) {
					list.add(new int[] { i, j });
				}
			}
		}

		dfs(0);

		System.out.println(paper == Integer.MAX_VALUE ? -1 : paper);
	}

	static void confetti(int r, int c, int dist, int value) {
		for (int i = r; i <= r + dist; i++) {
			for (int j = c; j <= c + dist; j++) {
				map[i][j] = value;
			}
		}
	}

	static void dfs(int count) {
		if (count >= paper) {
			return;
		}

		int r = -1, c = -1;
		
		boolean found = false;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					r = i;
					c = j;
					found = true;
					break;
				}
			}
			if (found)
				break;
		}
		
	    if (!found) {
	        paper = Math.min(paper, count);
	        return;
	    }
		
		for (int i = 4; i >= 0; i--) {
			if (paperCount[i] == 0) {
				continue;
			}

			if (canAttach(r, c, i)) {

				confetti(r, c, i, 0);
				paperCount[i]--;
				
				dfs(count + 1);
				
				confetti(r, c, i, 1);
				paperCount[i]++;
			}

		}
	}

	static boolean canAttach(int r, int c, int dist) {
		for (int i = r; i <= r + dist; i++) {
			for (int j = c; j <= c + dist; j++) {
				if (!isIn(i, j) || map[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					return false;
				}
			}
		}

		return true;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
