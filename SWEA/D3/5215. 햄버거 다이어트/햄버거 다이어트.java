import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int limit = 0;
		int num = 0;
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			max = 0;
			num = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());

			int ingredient[][] = new int[num][2];

			for (int i = 0; i < num; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken());
				ingredient[i][1] = Integer.parseInt(st.nextToken());
			}
			recursive(0, 0, ingredient, 0, limit);
			System.out.println("#" + t + " " + max);
			
	
		}

	}

	static void recursive(int idx, int cal, int[][] ingredient, int point, int limit) {
		if (cal > limit) {
			return;
		} else if (idx == ingredient.length) {
			max = Math.max(max, point);
			return;
		} 
		
		max = Math.max(max, point);
		
		recursive(idx + 1, cal+ingredient[idx][1], ingredient, point+ingredient[idx][0], limit);
		
		recursive(idx + 1, cal, ingredient, point, limit);
		
//		Math.max(max, point);
	}
}