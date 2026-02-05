import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N = 0;
	static int M = 0;
	static int sel[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sel = new int[M];

		comb(0, 1);
	}

	public static void comb(int depth, int start) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = start; i <= N; i++) {
			sel[depth] = i;
			comb(depth + 1, i + 1);
		}
	}
}
