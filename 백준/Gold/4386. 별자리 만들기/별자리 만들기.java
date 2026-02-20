import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n; // 별의 개수
	static double[][] stars;
	static ArrayList<Star>[] graph;
	static boolean[] visited;

	static class Star {
		int to;
		double cost;
		public Star(int to, double cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		stars = new double[n][n];
		graph = new ArrayList[n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			stars[i][0] = x;
			stars[i][1] = y;
			
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j <= n - 1; j++) {
				double from_x = stars[i][0];
				double from_y = stars[i][1];

				double to_x = stars[j][0];
				double to_y = stars[j][1];

				double cost = Math.sqrt((from_x - to_x) * (from_x - to_x) + (from_y - to_y) * (from_y - to_y));
				graph[i].add(new Star(j, cost));
				graph[j].add(new Star(i, cost));
			}
		}

		prim();
	}

	static void prim() {
		PriorityQueue<Star> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
		pq.offer(new Star(0, 0));
		double result = 0;

		while (!pq.isEmpty()) {
			Star now = pq.poll();

			if(visited[now.to]) {
				continue;
			}
			
			visited[now.to] = true;
			result += now.cost;
			
			for (Star next : graph[now.to]) {
				if(!visited[next.to]) {
					pq.offer(next);
				}
			}
		}

		System.out.println(result);
	}
}
