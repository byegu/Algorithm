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
			
			int sum = 0;
			int max = 0;
			int men = 0;
			
			for (int i = 0; i < 6; i++) {
				int now = Integer.parseInt(st.nextToken());
				sum += now;
				if (max < now) {
					max = now;
				}
			}
			men = max+1;
			
			while ((sum+men)%7 != 0) {
				men++;
			}
			
			System.out.println(men);
		}
	}
}