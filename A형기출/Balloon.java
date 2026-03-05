import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Balloon {
	static int N;
	static int arr[];
	static int dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+2];
		dp = new int[N+2][N+2];
		
		arr[0] = 1;
		arr[N+1] = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i < N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int len = 1; len <= N; len++) {
			for (int left = 1; left + len - 1 <= N; left++) {
				int right = left + len - 1;
				
				for (int i = left; i <= right; i++) {
					dp[left][right] = Math.max(dp[left][right]
							, dp[left][i-1] + dp[i+1][right] + arr[left-1] * arr[i] * arr[right+1]);
				}
			}
		}
		System.out.println(dp[1][N]);
	}
}




/*
int n = 5;
int[] dp = new int[n+1];

dp[1] = 1;
dp[2] = 2;

for (int i = 3; i <= n; i++) {
    dp[i] = dp[i-1] + dp[i-2];
}

System.out.println(dp[n]);
*/