import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N = 0;
	static int M = 0;
	static int sel[];
	static boolean arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new boolean[N+1];
		sel = new int[M];
		for (int i = 1; i <= N; i++) {
			arr[i] = true;
		}
		perm(0);
	}
	
	public static void perm(int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return ;
		}
		
		for (int i = 1; i <= N; i++) {
			if (!arr[i]) {
				continue;
			}
			sel[depth] = i;
			arr[i] = false;
			perm(depth+1);
			arr[i] = true;
		}
	}
}
