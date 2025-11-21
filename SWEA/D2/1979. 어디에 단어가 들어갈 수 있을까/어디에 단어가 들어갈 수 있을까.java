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
			int K = Integer.parseInt(st.nextToken());

			int puzzle[][] = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					puzzle[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int count = 0;
			int result = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (count == K && puzzle[i][j] == 0) {
						result++;
						count = 0;
						continue;
					}
					if (count != K && puzzle[i][j] == 0) {
						count = 0;
					} else {
						count++;
					}

				}
				if (count == K) {
					result++;
				}

				count = 0;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (count == K && puzzle[j][i] == 0) {
						result++;
						count = 0;
						continue;
					}
					if (count != K && puzzle[j][i] == 0) {
						count = 0;
					} else {
						count++;
					}

				}
				if (count == K) {
					result++;
				}
				count = 0;
			}

			System.out.println("#" + (t + 1) + " " + result);
		}
	}

}