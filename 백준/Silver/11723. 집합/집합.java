import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        int S = 0;

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("add")) {
                int x = Integer.parseInt(st.nextToken());
                S |= (1 << x);

            } else if (cmd.equals("remove")) {
                int x = Integer.parseInt(st.nextToken());
                S &= ~(1 << x);

            } else if (cmd.equals("check")) {
                int x = Integer.parseInt(st.nextToken());
                sb.append((S & (1 << x)) != 0 ? 1 : 0).append('\n');

            } else if (cmd.equals("toggle")) {
                int x = Integer.parseInt(st.nextToken());
                S ^= (1 << x);

            } else if (cmd.equals("all")) {
                S = (1 << 21) - 2;

            } else if (cmd.equals("empty")) {
                S = 0;
            }
        }
        System.out.print(sb.toString());
    }
}