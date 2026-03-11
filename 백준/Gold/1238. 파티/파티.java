import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 학생 수
	static int M; // 단방향 도로 수
	static int X; // 마을 번호
	
	static class City {
		int to;
		int cost;
		
		public City(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static ArrayList<City>[] graph;
	static ArrayList<City>[] graph2;
	static int[] dist;
	static int[] dist2;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		graph2 = new ArrayList[N+1];
		dist = new int[N+1];
		dist2 = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[from].add(new City(to, cost));
			graph2[to].add(new City(from, cost));
		}
		
		dijkstra();
		dijkstra2();
		
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, dist[i] + dist2[i]);
		}
		
		System.out.println(max);
	}
	
	static void dijkstra() {
		PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.cost, c2.cost));
		pq.offer(new City(X, 0));
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X] = 0;
		
		while (!pq.isEmpty()) {
			City now = pq.poll();
			
			if (dist[now.to] < now.cost) {
				continue;
			}
			
			for (City next : graph[now.to]) {
				int nextCost = dist[now.to] + next.cost;
				
				if (dist[next.to] > nextCost) {
					pq.offer(new City(next.to, nextCost));
					dist[next.to] = nextCost;
				}
			}
		}
	}
	static void dijkstra2() {
		PriorityQueue<City> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.cost, c2.cost));
		pq.offer(new City(X, 0));
		Arrays.fill(dist2, Integer.MAX_VALUE);
		dist2[X] = 0;
		
		while (!pq.isEmpty()) {
			City now = pq.poll();
			
			if (dist2[now.to] < now.cost) {
				continue;
			}
			
			for (City next : graph2[now.to]) {
				int nextCost = dist2[now.to] + next.cost;
				if (dist2[next.to] > nextCost) {
					pq.offer(new City(next.to, nextCost));
					dist2[next.to] = nextCost;
				}
			}
		}
	}
}
