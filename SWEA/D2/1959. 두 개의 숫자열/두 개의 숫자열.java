import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int A[] = new int[N];
			int B[] = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			int result = 0;

			if (N < M) {
				for (int i = 0; i <= M - N; i++) {
					int sum = 0;
					for (int j = 0; j < N; j++) {
						sum += A[j] * B[i + j];
					}
					result = Math.max(result, sum);
				}
			} else if (N > M) {
				for (int i = 0; i <= N - M; i++) {
					int sum = 0;
					for (int j = 0; j < M; j++) {
						sum += B[j] * A[i + j];
					}

					result = Math.max(result, sum);
				}
			} else {
				for (int i = 0; i < N; i++) {
					result += A[i] * B[i];
				}
			}
			System.out.println("#" + t + " " + result);
		}
	}
}