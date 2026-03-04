import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] operator; // 덧셈 뺄셈 곱셈 나눗셈
	static int N;
	static int[] operand;

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	static int[] sel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		operand = new int[N];
		operator = new int[4];
		sel = new int[N - 1];
		for (int i = 0; i < N; i++) {
			operand[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.fill(sel, -1);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0);
		System.out.println(max + "\n" + min);
	}

	static void dfs(int depth) {
		if (depth == N - 1) {
			int sum = calc();
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operator[i] != 0) {
				sel[depth] = i;
				operator[i]--;

				dfs(depth + 1);
				operator[i]++;
			}
		}
	}

	static int calc() {
	    int result = operand[0];

	    for (int i = 0; i < N - 1; i++) {
	        if (sel[i] == 0) {
	            result = result + operand[i + 1];
	        } else if (sel[i] == 1) {
	            result = result - operand[i + 1];
	        } else if (sel[i] == 2) {
	            result = result * operand[i + 1];
	        } else if (sel[i] == 3) {
	            result = result / operand[i + 1];
	        }
	    }

	    return result;
	}
}
