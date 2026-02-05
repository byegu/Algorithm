import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N = 0;

	static Point[] arr;
	static Point[] result;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()) + 2;
			min = Integer.MAX_VALUE;
			arr = new Point[N];
			result = new Point[N];
			visited = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				arr[i] = new Point(x, y);
			}

			result[0] = arr[1];
			result[N-1] = arr[0];
			
			perm(1);
			System.out.println("#" + t + " " + min);
		}
	}

	public static void perm(int depth) {
		int dist = 0;
		if (depth == N-1) {
			for (int i = 0; i < result.length-1; i++) {
				int x = Math.abs(result[i+1].x - result[i].x);
				int y = Math.abs(result[i+1].y - result[i].y);
				dist += x+y;
			}
			min = Math.min(min, dist);
			return;
		}
		
		for (int i = 2; i < arr.length; i++) {
			if (visited[i]) {
				continue;
			}
			
			visited[i] = true;
			result[depth] = arr[i];
			perm(depth+1);
			visited[i] = false;
		}
	}
}
