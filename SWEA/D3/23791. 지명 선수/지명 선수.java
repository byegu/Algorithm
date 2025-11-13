import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] A = new int[N];
			int[] B = new int[N];
			char[] team = new char[N];
			int count = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i ++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i ++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			int a_idx = 0;
			int b_idx = 0;
			while (true) {
				if (count == N) break;
				while (true) {
					if (team[A[a_idx]-1] == 0) {
						team[A[a_idx]-1] = 'A';
						count++;
						break;
					} else if (team[A[a_idx]-1] != 0) {
						a_idx++;
					}
				}
				
				if (count == N) break;
				while (true) {
					if (team[B[b_idx]-1] == 0) {
						team[B[b_idx]-1] = 'B';
						count++;
						break;
					} else if (team[B[b_idx]-1] != 0) {
						b_idx++;
					}
				}
				
			}
			
			for (char c : team) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
}
