import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] prefix = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					prefix[i][j] = arr[i - 1][j - 1] + prefix[i][j - 1] + prefix[i - 1][j] - prefix[i - 1][j - 1];
				}
			}

			int max = 0;
			for (int i = M; i <= N; i++) {
				for (int j = M; j <= N; j++) {
					int sum = prefix[i][j] - prefix[i-M][j] - prefix[i][j-M] + prefix[i-M][j-M];
					
					max = Math.max(max, sum);
				}
			}
			
			System.out.println("#" + (t+1) + " " + max);
		}
	}
}