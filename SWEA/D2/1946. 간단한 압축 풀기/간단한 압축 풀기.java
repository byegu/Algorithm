import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String c = st.nextToken();
                int k = Integer.parseInt(st.nextToken());

                for (int j = 0; j < k; j++) {
                    sb.append(c);
                }
            }

            System.out.println("#" + t);

            String full = sb.toString();
            int length = full.length();

            for (int i = 0; i < length; i++) {
                System.out.print(full.charAt(i));
                if ((i + 1) % 10 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }
}