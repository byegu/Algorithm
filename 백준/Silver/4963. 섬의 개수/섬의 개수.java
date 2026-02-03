
import java.io.*;
import java.util.*;

public class Main {
	static int island = 0;
	static int dr[] = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int dc[] = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static int H, W;
	static int temp = 0;

//	static int start_r = 0, start_c = 0;

	static boolean visited[][];

	static class Point {
		int r;
		int c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if (H == 0 && W == 0) {
				return;
			}
			int arr[][] = new int[H][W];

//			start_r = 0;
//			start_c = 0;
			temp = 0;
			island = 0;
			visited = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			traversal(arr);
			System.out.println(island);
		}
	}

	static void bfs(int[][] arr, int r, int c) {
//		if (start_r == H - 1 && start_c == W - 1) {
//			return;
//		}
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));

		while (!q.isEmpty()) {
			Point p = q.poll();
			int pr = p.r;
			int pc = p.c;
			for (int i = 0; i < 8; i++) {
				if (!visited[pr][pc] && arr[pr][pc] == 1) {
					temp++;
				}
				int nr = pr + dr[i];
				int nc = pc + dc[i];
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc] && arr[nr][nc] == 1) {
					visited[nr][nc] = true;
					q.offer(new Point(nr, nc));
					temp++;
				}
			}
		}

		if (temp > 0) {
			island++;
		}

//		if (start_c == W - 1) {
//			start_c = 0;
//			start_r += 1;
//		} else {
//			start_c += 1;
//		}
//		temp = 0;
//
//		bfs(arr);
	}
	
	static void traversal(int[][] arr) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					bfs(arr, i, j);
				}
			}
		}
	}

}
