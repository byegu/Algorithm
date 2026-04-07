import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int schedule[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		schedule = new int[N + 1][2];
		int dp[] = new int[N + 2];

		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < N + 1; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			int days = schedule[i][0] + i;
			
			if (days <= N + 1) {	
				dp[days] = Math.max(dp[days], dp[i] + schedule[i][1]);
			}
		}

		for (int i = 1; i <= N+1; i++) {
		    dp[i] = Math.max(dp[i], dp[i-1]);
		}
		System.out.println(dp[N+1]);
	}
}
