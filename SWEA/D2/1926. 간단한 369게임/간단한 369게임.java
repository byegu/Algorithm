import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			char[] num = (i + "").toCharArray();
			String s = "";
			
			for (char c : num) {
				if (c == '3' || c == '6' || c == '9') s += "-";
			}
			
			if (s.isEmpty()) {
				System.out.print(i + " ");
			} else {
				System.out.print(s + " ");
			}
			
		}

	}

}
