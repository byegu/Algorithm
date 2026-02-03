import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };
	static boolean[][] visited;
	static int count = 0;
	static Set<Character> set;
	
	static int R = 0;
	static int C = 0;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			set = new HashSet<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			max = 0;
			visited = new boolean[R][C];
			char[][] arr = new char[R][C];

			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					arr[i][j] = str.charAt(j);
				}
			}
			
			visited[0][0] = true;
			set.add(arr[0][0]);
			count = 1;

			dfs(arr, 0, 0);
			System.out.println("#" + t + " " + max);
		}
	}

	static void dfs(char[][] arr, int r, int c) {
		max = Math.max(max, count);
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (isIn(nr, nc) && !visited[nr][nc]) {
				if (!set.contains(arr[nr][nc])) {
					count++;
					set.add(arr[nr][nc]);
					visited[nr][nc] = true;
					
					dfs(arr, nr, nc);
					
					visited[nr][nc] = false;
					set.remove(arr[nr][nc]);
					count--;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C) {
			return true;
		}
		return false;
	}
}
