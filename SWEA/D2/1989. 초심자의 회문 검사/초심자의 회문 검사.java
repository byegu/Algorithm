import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			char[] c = br.readLine().toCharArray();
			int result = 1;
			
			for (int i = 0; i < c.length; i++) {
				if(c[i] != c[(c.length-1)-i]) {
					result = 0;
				}
			}
			System.out.println("#" + (t+1) + " " + result);
		}
	}
}
