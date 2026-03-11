import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int W[]; // i번째 논에 우물을 팔 때 드는 비용

	static class Paddy {
		int to;
		int cost;

		public Paddy(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static ArrayList<Paddy>[] graph;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		W = new int[N+2];
		graph = new ArrayList[N+2];
		visited = new boolean[N+2];
		graph[0] = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			W[i] = Integer.parseInt(br.readLine());
			graph[i] = new ArrayList<>();
			
			graph[0].add(new Paddy(i, W[i]));
		}
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					st.nextToken();
					continue;
				}
				
				int from = i;
				int to = j;
				int cost = Integer.parseInt(st.nextToken());
				
				graph[from].add(new Paddy(to, cost));
			}
		}
		
		prim();
	}

	static void prim() {
		PriorityQueue<Paddy> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.cost, p2.cost));
		pq.offer(new Paddy(0, 0));
		
		int result = 0;
		
		while (!pq.isEmpty()) {
			Paddy now = pq.poll();
			
			if (visited[now.to]) {
				continue;
			}
			
			visited[now.to] = true;
			result += now.cost;
			
			for (Paddy next : graph[now.to]) {
				if (!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		System.out.println(result);
	}
}
