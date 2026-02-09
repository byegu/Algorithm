import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static ArrayList<Integer>[] list;
	static int[] visited;
	static int size;
	static int start;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			size = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			list = new ArrayList[101];
			visited = new int[101];
			result = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 101; i++) {
				list[i] = new ArrayList();
			}
			for (int i = 0; i < size / 2; i++) {
				list[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}

			bfs();
			System.out.println("#" + t + " " + result);
		}
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = 1;
		int max = 0;
		while (!q.isEmpty()) {
			for (int i = 0; i < q.size(); i++) {
				int node = q.poll();
				int count = visited[node];
				for (Integer idx : list[node]) {
					if (visited[idx] == 0) {
						visited[idx] = count + 1;
						max = Math.max(visited[idx], max);
						q.offer(idx);
					}
				}
			}
		}
		
		for (int i = 0; i < 101; i++) {
			if (visited[i] == max) {
				result = Math.max(i, result);
			}
		}
	}

}
