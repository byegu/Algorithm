import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlowerPot {
	static int N; // 화분 개수
	static int P; // 비료를 주었을 때 덜 자라는 수치
	static int sum;
	static int max;
	
	static int[] first;
	static int[] second;
	static int[] sel;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			first = new int[N];
			second = new int[N];
			sel = new int[N];
			sum = 0;
			max = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				first[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				second[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, false, false);
			System.out.println("#" + t + " " + max);
		}
	}
	
	static void dfs(int depth, int idx, boolean firstFlag, boolean secondFlag) {
		if (depth == N) {
			for (int i = 0; i < N; i++) {
				sum += sel[i];
			}
			max = Math.max(max, sum);
			sum = 0;
			return;
		}
		
		if (firstFlag) {
			sel[depth] = first[idx] - P;;
			dfs(depth + 1, idx + 1, true, false);
		}
		if (!firstFlag) {
			sel[depth] = first[idx];
			dfs(depth + 1, idx + 1, true, false);
		}
		if (secondFlag) {
			sel[depth] = second[idx] - P;
			dfs(depth + 1, idx + 1, false, true);
		}
		if (!secondFlag) {
			sel[depth] = second[idx];
			dfs(depth + 1, idx + 1, false, true);
			
		}
	}

}
