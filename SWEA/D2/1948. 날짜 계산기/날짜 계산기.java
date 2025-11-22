import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		int date[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int mon = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			int nmon = Integer.parseInt(st.nextToken());
			int nday = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			
			if (mon != nmon) {
				for (int i = mon + 1; i < nmon; i++) {
					sum += date[i];
				}
				
				sum += (date[mon] - day) + nday + 1;
			} else {
				sum = nday - day + 1;
			}
			
			System.out.println("#" + (t+1) + " " + sum);
		}
	}
}
