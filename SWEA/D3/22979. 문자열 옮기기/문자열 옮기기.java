import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			String S = br.readLine();

			int K = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = 0;
			
			for (int i = 0; i < K; i++) {
				X += Integer.parseInt(st.nextToken()) % S.length();
			}

			if (X > 0) {
				for (int n = 0; n < X; n++) {
					String sub = S.substring(0, 1);
					S = S.substring(1).concat(sub);
				}
			} else if (X < 0) {
				X = Math.abs(X);
				for (int n = 0; n < X; n++) {
					String sub = S.substring(S.length() - 1);
					S = sub + S.substring(0, S.length() - 1);
				}
			}
			System.out.println(S);
		}
	}
}
