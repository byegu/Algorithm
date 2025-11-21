import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int change[] = new int[8];
			int won[] = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
			
			for (int i = 0; i < 8; i++) {
				if (N/won[i] > 0) {
					change[i] = N/won[i];
					N -= change[i] * won[i];
				}
			}
			
			System.out.println("#" + (t+1));
			
			for (int i : change) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}