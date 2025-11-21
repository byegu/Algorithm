import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int hour = Integer.parseInt(st.nextToken());
			int min = Integer.parseInt(st.nextToken());

			hour += Integer.parseInt(st.nextToken());
			min += Integer.parseInt(st.nextToken());

			if (hour % 12 != 0) {
				hour %= 12;
			} else if (hour % 12 == 0) {
				hour = 12;
			}
			
			if (min/60 > 0) {
				hour += min/60;
				min %= 60;
			}
			
			System.out.println("#" + (t+1) + " " + hour + " " + min);
		}
	}
}