import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	static class Planet {
		int to;
		int cost;
		
		public Planet(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static boolean[] visited;
	static ArrayList<Planet>[] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N+1];
		graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
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
				
				graph[from].add(new Planet(to, cost));
				graph[to].add(new Planet(from, cost));
			}
		}
		
		prim();
	}

	static void prim() {
		PriorityQueue<Planet> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.cost, p2.cost));
		pq.offer(new Planet(1, 0));
		
		long result = 0;
		
		while(!pq.isEmpty()) {
			Planet now = pq.poll();
			
			if (visited[now.to]) {
				continue;
			}
			
			visited[now.to] = true;
			result += now.cost;
			
			for (Planet next : graph[now.to]) {
				if (!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		System.out.println(result);
	}
}
