import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int sudoku[][] = new int[9][9];

			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int flag = 1;
			int sum = 0;

			for (int i = 0; i < 9; i += 3) {
				for (int j = 0; j < 3; j++) {
					sum += sudoku[i][j] + sudoku[i + 1][j] + sudoku[i + 2][j];
				}
				if (sum != 45) {
					flag = 0;
					break;
				}
				sum = 0;
				for (int j = 3; j < 6; j++) {
					sum += sudoku[i][j] + sudoku[i + 1][j] + sudoku[i + 2][j];
				}
				if (sum != 45) {
					flag = 0;
					break;
				}
				sum = 0;
				for (int j = 6; j < 9; j++) {
					sum += sudoku[i][j] + sudoku[i + 1][j] + sudoku[i + 2][j];
				}
				if (sum != 45) {
					flag = 0;
					break;
				}
				sum = 0;
			}

			if (flag != 0) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						sum += sudoku[i][j];
					}
					if (sum != 45) {
						flag = 0;
						break;
					}
					sum = 0;
				}
			}

			if (flag != 0) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						sum += sudoku[j][i];
					}
					if (sum != 45) {
						flag = 0;
						break;
					}
					sum = 0;
				}
			}

			System.out.println("#" + (t + 1) + " " + flag);
		}
	}
}
