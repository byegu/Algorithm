import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N, M;
	static int[] parent;
	static int group;
	static boolean[] list;
	
	static void makeSet(int x) {
		parent = new int[x+1];
		
		for (int i = 1; i <= x; i++) {
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
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list = new boolean[N+1];
			makeSet(N);
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			for (int i = 1; i < N+1; i++) {
				list[find(i)] = true;
			}
			
			group = 0;
			
			for (int i = 1; i < N+1; i++) {
				if (list[i]) {
					group++;
				}
			}
			
			System.out.println("#" + t + " " + group);
		}
	}

}
