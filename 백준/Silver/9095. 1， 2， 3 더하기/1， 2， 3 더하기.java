import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int T;
	static int count;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			count = 0;
			
			solve(0);
			
			System.out.println(count);
		}

	}
	static void solve(int n) {
		if (N == n) {
			count++;
			return;
		}
		
		if (n > N) {
			return;
		}
		
		solve(n+3);
		solve(n+2);
		solve(n+1);
	}
}
