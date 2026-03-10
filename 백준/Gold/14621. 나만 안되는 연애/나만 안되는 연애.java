import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static class Univ {
		int to;
		int cost;
		
		public Univ(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static int u, v, d;
	static ArrayList<Univ>[] graph;
	static boolean[] visited;
	static char[] univTypes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		univTypes = new char[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			univTypes[i] = st.nextToken().charAt(0);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Univ(to, cost));
			graph[to].add(new Univ(from, cost));
		}
		
		prim();
	}
	
	static void prim() {
		PriorityQueue<Univ> pq = new PriorityQueue<>((u1, u2) -> u1.cost - u2.cost);
		pq.add(new Univ(1, 0));
		
		int result = 0;
		
		while(!pq.isEmpty()) {
			Univ now = pq.poll();
			
			if (visited[now.to]) {
				continue;
			}
			
			visited[now.to] = true;
			result += now.cost;
			
			for (Univ next : graph[now.to]) {
				if (!visited[next.to] && univTypes[now.to] != univTypes[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				result = -1;
			}
		}
		
		System.out.println(result);
	}
}
