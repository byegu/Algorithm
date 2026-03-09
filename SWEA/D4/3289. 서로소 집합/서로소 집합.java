import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int n, m;
	static int[] parent;

	static void makeSet(int x) {
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	static int find(int x) {
		if (parent[x] != x) {
			return parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);

		if (ra != rb) {
			parent[rb] = ra;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			makeSet(n);
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (op == 0) {
					union(a, b);
				} else if (op == 1) {
					if (find(a) == find(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			
			System.out.println(sb);
		}
	}
}
