import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, R, Q; // 정점의 수, 루트 번호, 쿼리의 수
	static List<Integer>[] connect; // 인접 리스트
	static List<Integer>[] children; // 각 노드의 자식 리스트
	static int[] parent;
	static int[] size;

	static void makeTree(int cur, int par) {
		for (int node : connect[cur]) {
			if (node != par) {
				children[cur].add(node);
				parent[node] = cur;
				makeTree(node, cur);
			}
		}
	}
	
	static void countSubtreeNodes(int cur) {
		size[cur] = 1;
		for (int node : children[cur]) {
			countSubtreeNodes(node);
			size[cur] += size[node];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		connect = new ArrayList[N + 1];
		children = new ArrayList[N + 1];
		parent = new int[N + 1];
		size = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			connect[i] = new ArrayList<>();
			children[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());

			connect[U].add(V);
			connect[V].add(U);
		}

		makeTree(R, -1);
		countSubtreeNodes(R);
		
		for (int i = 0; i < Q; i++) {
			int U = Integer.parseInt(br.readLine());
			System.out.println(size[U]);
		}
	}
}
