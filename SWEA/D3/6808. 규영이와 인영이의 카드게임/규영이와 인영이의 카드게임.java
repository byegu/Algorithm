import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static boolean used[] = new boolean[9];
	static int gyu[] = new int[9];
	static int in[] = new int[9];

	static int win = 0;
	static int lose = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			boolean card[] = new boolean[19];
			for (int i = 0; i < 9; i++) {
				int current = Integer.parseInt(st.nextToken());
				gyu[i] = current;

				card[current] = true;
			}
			int idx = 0;
			for (int j = 1; j < 19; j++) {
				if (idx == in.length) {
					break;
				}
				if (!card[j]) {
					in[idx++] = j;
				}
			}
			win = 0;
			lose = 0;
			perm(0, 0, 0);
			System.out.println("#" + t + " " + win + " " + lose);
		}
	}

	public static void perm(int idx, int gyu_score, int in_score) {
		if (idx == 9) {
			if (gyu_score > in_score) {
				win++;
			} else if (gyu_score < in_score) {
				lose++;
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (used[i]) {
				continue;
			}

			used[i] = true;
			int sum = gyu[idx] + in[i];

			if (gyu[idx] > in[i]) {
				perm(idx + 1, gyu_score + sum, in_score);
			} else if (gyu[idx] < in[i]) {
				perm(idx + 1, gyu_score, in_score + sum);
			}

			used[i] = false;
		}
	}
}
