import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int count = 0;
			double K_grade = 0;
			String[] grade = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
			double[] student = new double[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				student[i] = (Double.parseDouble(st.nextToken())*0.35)
						+ (Double.parseDouble(st.nextToken())*0.45)
						+ (Double.parseDouble(st.nextToken())*0.2);
				
				if (i == K-1) K_grade = student[i];
			}
			
			for (int i = 0; i < N; i++) {
				if (student[i] > K_grade) {
					count++;
				}
			}
			
			int result = (count/(N/10));
			
			System.out.println("#" + (t+1) + " " + grade[result]);
			
		}
	}
}
