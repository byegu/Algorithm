import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			char[] c = br.readLine().toCharArray();
			String s = "";
			String s2 = "";
			
			for (int i = 0; i < 10; i++) {
				s += c[i];
				s2 = "";
				for (int j = i+1; j <= i*2+1; j++) {
					s2 += c[j];
				}
				
				if (s.equals(s2)) {
					System.out.println("#" + t + " " + (i+1));
					break;
				}
			}
		}
	}
}
