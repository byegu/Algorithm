import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int coin[] = new int[N];
		int dp[] = new int[K + 1];

		for (int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i < K + 1; i++) {
			dp[i] = K + 1;
		}

		dp[0] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = coin[i]; j <= K; j++) {
				dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
			}
		}

		if (dp[K] == K+1) {
			System.out.println(-1);
		} else {
			System.out.println(dp[K]);
		}
	}
}
