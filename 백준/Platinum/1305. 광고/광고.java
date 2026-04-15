import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int[] makeLPS(String pattern) {
		int m = pattern.length();
		int lps[] = new int[m];

		int len = 0;
		int i = 1;

		while (i < m) {
			if (pattern.charAt(i) == pattern.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}

		return lps;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String pattern = br.readLine();
		
		int[] lps = makeLPS(pattern);
		
		System.out.println(L - lps[L-1]);
	}
}
