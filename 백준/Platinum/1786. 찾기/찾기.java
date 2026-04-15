import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static int[] makeLPS(String pattern) {
		int m = pattern.length();
		int[] lps = new int[m];

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

	public static void kmpSearch(String text, String pattern) {
		int[] lps = makeLPS(pattern);

		int i = 0;
		int j = 0;

		int count = 0;
		List<Integer> result = new ArrayList<>();

		while (i < text.length()) {
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			}

			if (j == pattern.length()) {
				count++;
				result.add(i - j + 1);
				j = lps[j - 1];
			} else if (i < text.length() && j < pattern.length() && text.charAt(i) != pattern.charAt(j)) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}

		System.out.println(count);
		for (Integer idx : result) {
			System.out.print(idx + " ");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		String pattern = br.readLine();

		kmpSearch(text, pattern);
	}
}
