import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int A = 0;
	static int B = 0;
	static boolean[] prime = new boolean[10000];
	static boolean[] visited = new boolean[10000];
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			min = Integer.MAX_VALUE;
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			prime = new boolean[10000];
			visited = new boolean[10000];
			isPrime();
			int count = bfs();
			System.out.println(count >= 0 ? count : "Impossible");
		}
	}

	static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		int[] dist = new int[10000];
		visited[A] = true;
		q.offer(A);

		while (!q.isEmpty()) {
			int num = q.poll();
			int[] arr = new int[4];
			arr[0] = num / 1000;
			arr[1] = num % 1000 / 100;
			arr[2] = num % 1000 % 100 / 10;
			arr[3] = num % 1000 % 100 % 10;
			if (num == B) {
				return dist[num];
			}
			for (int i = 0; i < 4; i++) {
				int[] temp = { arr[0], arr[1], arr[2], arr[3] };
				for (int j = 0; j < 10; j++) {
					if (i == 0 && j == 0)
						continue;

					temp[i] = j;

					int next = temp[0] * 1000 + temp[1] * 100 + temp[2] * 10 + temp[3];

					if (!visited[next] && prime[next]) {
						visited[next] = true;
						dist[next] = dist[num] + 1;
						q.offer(next);
					}
				}
			}
		}
		return -1;
	}

	static void isPrime() {
		Arrays.fill(prime, true);

		prime[0] = prime[1] = false;

		for (int i = 2; i * i < 10000; i++) {
			if (prime[i]) {
				for (int j = i * i; j < 10000; j += i) {
					prime[j] = false;
				}
			}
		}

		for (int i = 0; i < 1000; i++)
			prime[i] = false;
	}
}
