import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			if (N == 2) {
				System.out.println("BA");
			} else if (N % 3 == 2) {
				System.out.print("BA");
				for (int i = 0; i < N / 3; i++) {
					System.out.print("BBA");
				}
				System.out.println();
			} else if (N % 3 == 0) {
				for (int i = 0; i < N / 3; i++) {
					System.out.print("BBA");
				}
				System.out.println();
			} else {
				System.out.println("impossible");
			}
		}
	}
}
