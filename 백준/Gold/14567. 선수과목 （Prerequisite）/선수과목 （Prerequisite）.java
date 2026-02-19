import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] inDegree;
	static ArrayList<Integer>[] graph;
	static int N; // 과목의 수
	static int M; // 선수 조건의 수

	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		inDegree = new int[N + 1];
		result = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[from].add(to);
			inDegree[to]++;
		}

		topologySort();
		
		for (int i = 1; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	static void topologySort() {
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 1; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}
		int semester = 1;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int now = q.poll();
				result[now] = semester;
				for (int next : graph[now]) {
					inDegree[next]--;
					if (inDegree[next] == 0) {
						q.offer(next);
					}
				}
			}
			semester++;
		}
	}
}
