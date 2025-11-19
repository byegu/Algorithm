import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] pascal = new int[N][N];
			pascal[0][0] = 1;
			for (int i = 1; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					if (j != 0) {
						pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
					} else if(j == 0) {
						pascal[i][j] = 1;
					}
				}
			}

			System.out.println("#" + (t+1));
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					System.out.print(pascal[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

}
