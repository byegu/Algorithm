import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] makeLPS(String s) {
		int[] lps = new int[s.length()];
		int j = 0;

		for (int i = 1; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != s.charAt(j)) {
				j = lps[j - 1];
			}
			if (s.charAt(i) == s.charAt(j)) {
				lps[i] = ++j;
			}
		}
		return lps;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int[] lps = makeLPS(s);
		int len = lps[s.length() - 1];

		while (len > 0) {
			boolean found = false;
			for (int i = 0; i < s.length() - 1; i++) {
				if (lps[i] == len) {
					found = true;
					break;
				}
			}

			if (found) {
				System.out.println(s.substring(0, len));
				return;
			}
			len = lps[len - 1];
		}
		System.out.println(-1);
	}
}