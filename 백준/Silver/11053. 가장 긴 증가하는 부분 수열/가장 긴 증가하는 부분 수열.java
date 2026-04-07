import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int	dp[] = new int[N];
		int seq[] = new int[N];
		int result = 1;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (seq[j] < seq[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
					result = Math.max(result, dp[i]);
				}
			}
		}
		
		System.out.println(result);
	}
}