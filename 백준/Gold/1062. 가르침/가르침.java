import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K; // 단어 개수, 가르칠 글자 개수
	static boolean[][] word;
	static boolean[] teach;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		word = new boolean[N][27];
		teach = new boolean[27];
		max = 0;

		if (K > 4) {
			for (int t = 0; t < N; t++) {
				String str = br.readLine();

				for (int i = 0; i < str.length(); i++) {
					int idx = str.charAt(i) - 97;
					word[t][idx] = true;
				}

			}
			teach['a' - 97] = teach['n' - 97] = teach['t' - 97] = teach['i' - 97] = teach['c' - 97] = true;

			dfs(0, 0, K - 5);

		}
		System.out.println(max);

	}

	static void dfs(int depth, int idx, int limit) {
		if (depth == limit) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (check(i)) {
					count++;
				}
			}
			max = Math.max(count, max);
			return;
		}

		for (int i = idx; i < 26; i++) {
			if (teach[i]) {
				continue;
			}
			teach[i] = true;
			dfs(depth + 1, i + 1, limit);
			teach[i] = false;
		}

	}

	static boolean check(int i) {
		for (int j = 0; j < word[i].length; j++) {
			if (!word[i][j]) {
				continue;
			}
			if (!teach[j]) {
				return false;
			}
		}
		return true;
	}
}
