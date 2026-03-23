import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L, C; // 암호의 길이, 알파벳 개수
	static char arr[];
	static char password[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[C];
		password = new char[L];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(arr);

		comb(0, 0);
	}

	static void comb(int depth, int start) {
		if (depth == L) {
			if (check()) {
				for (char c : password) {
					System.out.print(c);
				}
				System.out.println();
			}
			return;
		}

		for (int i = start; i < C; i++) {
			password[depth] = arr[i];
			comb(depth + 1, i + 1);
		}
	}

	static boolean check() {
		int vow = 0;
		int con = 0;

		for (int i = 0; i < L; i++) {
			char c = password[i];
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				vow++;
			}
		}

		con = L - vow;

		if (con >= 2 && vow >= 1) {
			return true;
		}

		return false;
	}
}