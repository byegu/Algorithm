import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int B, W, X, Y, Z;

			StringTokenizer st = new StringTokenizer(br.readLine());
			B = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			Z = Integer.parseInt(st.nextToken());


            int sameColor = B * X + W * Y;
            int mixed = 2 * Math.min(B, W) * Z + (B - Math.min(B, W)) * X + (W - Math.min(B, W)) * Y;

            int point = Math.max(sameColor, mixed);

			System.out.println(point);
		}
	}
}
