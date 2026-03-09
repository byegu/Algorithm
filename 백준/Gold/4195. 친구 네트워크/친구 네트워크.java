import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int F;

	static Map<String, String> parent = new HashMap<>();
	static Map<String, Integer> size = new HashMap<>();

	static void makeSet(String x) {
	    parent.put(x, x);
	    size.put(x, 1);
	}

	static String find(String x) {
	    if (!parent.get(x).equals(x)) {
	        parent.put(x, find(parent.get(x)));
	    }
	    return parent.get(x);
	}

	static int union(String a, String b) {
	    String ra = find(a);
	    String rb = find(b);

	    if (!ra.equals(rb)) {
	        parent.put(rb, ra);
	        size.put(ra, size.get(ra) + size.get(rb));
	    }

	    return size.get(ra);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			F = Integer.parseInt(br.readLine());
			parent = new HashMap<>();

			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String friend1 = st.nextToken();
				String friend2 = st.nextToken();

				if (!parent.containsKey(friend1)) makeSet(friend1);
				if (!parent.containsKey(friend2)) makeSet(friend2);

				System.out.println(union(friend1, friend2));
			}
		}
	}
}
