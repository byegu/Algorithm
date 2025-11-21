import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			int temp[][] = new int[N][N];
			String matrix[][] = new String[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					temp[i][j] = Integer.parseInt(st.nextToken());
					matrix[i][j] = "";
				}
			}
			int p = 0;
			
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i >= 0; i--) {
					matrix[p][0] += temp[i][j];
				}
				p++;
			}
			
			p = 0;
			for (int i = N - 1; i >= 0; i--) {
				for (int j = N - 1; j >= 0; j--) {
					matrix[p][1] += temp[i][j];
				}
				p++;
			}
			
			p = 0;
			for (int j = N - 1; j >= 0; j--) {
				for (int i = 0; i < N; i++) {
					matrix[p][2] += temp[i][j];
				}
				p++;
			}
			
			System.out.println("#" + t);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
