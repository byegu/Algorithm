import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[N + 1];
		int[][] dp = new int[N + 1][2];

		for (int i = 1; i < N + 1; i++) {
			int point = Integer.parseInt(br.readLine());
			stairs[i] = point;
		}

		dp[0][0] = stairs[0];
		dp[0][1] = stairs[0];
		dp[1][0] = stairs[1];
		dp[1][1] = stairs[1];
		
		for (int i = 2; i < N + 1; i++) {
			dp[i][0] = Math.max(dp[i-2][0] + stairs[i], dp[i-2][1] + stairs[i]);
			dp[i][1] = dp[i-1][0] + stairs[i];
		}
		
		int result = Math.max(dp[N][0], dp[N][1]);
		System.out.println(result);
	}

}
