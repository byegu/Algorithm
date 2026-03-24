import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static final int N = 5;
	static char[][] map = new char[N][N];
	static int[] force = new int[7];
	static int result = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		dfs(0, 0);

		System.out.println(result);
	}

	static void dfs(int depth, int start) {
		if (depth == 7) {
			if (check()) {
				result++;
			}
			return;
		}

		for (int i = start; i < N*N; i++) {
			force[depth] = i;
			dfs(depth+ 1, i + 1);
		}
	}

	static boolean check() {
	    int scnt = 0;

	    boolean[][] selected = new boolean[N][N];

	    for (int i = 0; i < 7; i++) {
	        int r = force[i] / N;
	        int c = force[i] % N;

	        selected[r][c] = true;

	        if (map[r][c] == 'S') scnt++;
	    }

	    if (scnt < 4) return false;

	    Queue<int[]> q = new ArrayDeque<>();
	    boolean[][] visited = new boolean[N][N];

	    int sr = force[0] / N;
	    int sc = force[0] % N;

	    q.add(new int[]{sr, sc});
	    visited[sr][sc] = true;

	    int cnt = 1;

	    while (!q.isEmpty()) {
	        int[] p = q.poll();

	        for (int d = 0; d < 4; d++) {
	            int nr = p[0] + dr[d];
	            int nc = p[1] + dc[d];

	            if (!isIn(nr, nc)) continue;
	            if (visited[nr][nc]) continue;
	            if (!selected[nr][nc]) continue;

	            visited[nr][nc] = true;
	            q.add(new int[]{nr, nc});
	            cnt++;
	        }
	    }

	    return cnt == 7;
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
