import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] staff;
	static boolean[] visited;

	static int N = 0;
	static int B = 0;

	static int sum = 0;
	static int gap = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			staff = new int[N];
			visited = new boolean[N];

			sum = 0;
			gap = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				staff[i] = Integer.parseInt(st.nextToken());
			}

			recursive(0);
			System.out.println("#" + t + " " + gap);
		}
	}

	public static void recursive(int idx) {
		if (idx == N) {
			sum = 0;
			for (int i = 0; i < N; i++) {
				if(visited[i]) {
					sum += staff[i];
				}
			}
			
			if (sum >= B) {
				gap = Math.min(gap, sum - B);
				sum = 0;
			}
			
			return;
		}
		visited[idx] = true;
		recursive(idx + 1);

		visited[idx] = false;
		recursive(idx + 1);

	}
}