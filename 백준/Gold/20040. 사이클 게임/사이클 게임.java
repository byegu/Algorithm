import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n; // 점의 개수
	static int m; // 차례의 수
	static int turn = 0;
	
	static int parent[];
	
	static void makeSet(int x) {
		parent = new int[x];
		
		for (int i = 0; i < x; i++) {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		makeSet(n);
		
		int count = 0;
		for (int i = 0; i < m; i++) {
		    count++;
		    st = new StringTokenizer(br.readLine());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    
		    if (find(a) == find(b)) {
		        turn = count;
		        break;
		    }

		    union(a, b);
		}

		System.out.println(turn);
	}

}
