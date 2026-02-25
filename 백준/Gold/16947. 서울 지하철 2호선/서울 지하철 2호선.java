import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Integer>[] graph;
	static int[] parent;
	static boolean[] state;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		parent = new int[N + 1];
		state = new boolean[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[from].add(to);
			graph[to].add(from);
		}

		dfs(1, -1);
		traversal();

	}

	static void bfs(int start) {
	    Queue<Integer> q = new ArrayDeque<>();
	    boolean[] visited = new boolean[N + 1];
	    int[] dist = new int[N + 1];

	    q.offer(start);
	    visited[start] = true;

	    while (!q.isEmpty()) {
	        int now = q.poll();

	        if (state[now]) {
	            System.out.print(dist[now] + " ");
	            return;
	        }

	        for (int next : graph[now]) {
	            if (!visited[next]) {
	                visited[next] = true;
	                dist[next] = dist[now] + 1;
	                q.offer(next);
	            }
	        }
	    }
	}

	static void dfs(int curr, int par) {
		visited[curr] = true;
		parent[curr] = par;

		for (int neighbor : graph[curr]) {

			if (!visited[neighbor]) {
				dfs(neighbor, curr);
			}

			else if (neighbor != par) {
				if (state[neighbor])
					continue;

				int x = curr;
				state[neighbor] = true;

				while (x != neighbor && x != -1) {
					state[x] = true;
					x = parent[x];
				}
			}
		}
	}

	static void traversal() {

		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
	}
}
