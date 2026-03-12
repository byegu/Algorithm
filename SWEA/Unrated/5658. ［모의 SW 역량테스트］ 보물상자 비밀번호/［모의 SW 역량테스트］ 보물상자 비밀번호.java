import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N, K; // 숫자 개수, K 번째로 큰 수
	static int limit;
	static char[] list;
	static int[] nums;
	static Set<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			limit = N / 4;
			list = new char[N];
			set = new HashSet<>();

			String str = br.readLine();
			for (int i = 0; i < N; i++) {
				list[i] = str.charAt(i);
			}

			for (int i = 0; i < N; i++) {
				String s = "";
				for (int j = 0; j < limit; j++) {
					int idx = i + j;
					
					if (idx >= N) {
						idx = (i+j) % limit;
					}
					
					s = s + list[idx];
				}
				set.add(s);
			}

			nums = new int[set.size() + 1];

			Iterator<String> it = set.iterator();

			int idx = 1;
			while (it.hasNext()) {
				int i = Integer.parseInt(it.next(), 16);
				nums[idx] = i;
				idx++;
			}

			Arrays.sort(nums);

			System.out.println("#" + t + " " + nums[set.size() + 1 - K]);
		}

	}

}
