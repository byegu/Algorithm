import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] cx;
    static int[] cy;
    static boolean[] visited;
    static boolean[][] connected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cx = new int[N + 1];
        cy = new int[N + 1];
        visited = new boolean[N + 1];
        connected = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            cx[i] = Integer.parseInt(st.nextToken());
            cy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            connected[from][to] = true;
            connected[to][from] = true;
        }

        prim();
    }

    static void prim() {
        double[] minEdge = new double[N + 1];
        Arrays.fill(minEdge, Double.MAX_VALUE);

        minEdge[1] = 0;

        double result = 0;

        for (int i = 1; i <= N; i++) {
            double min = Double.MAX_VALUE;
            int minVertex = -1;

            for (int j = 1; j <= N; j++) {
                if (!visited[j] && minEdge[j] < min) {
                    min = minEdge[j];
                    minVertex = j;
                }
            }

            visited[minVertex] = true;
            result += min;

            for (int j = 1; j <= N; j++) {
                if (!visited[j]) {
                    double cost;

                    if (connected[minVertex][j]) {
                        cost = 0;
                    } else {
                        long dx = cx[minVertex] - cx[j];
                        long dy = cy[minVertex] - cy[j];
                        cost = Math.sqrt(dx * dx + dy * dy);
                    }

                    if (minEdge[j] > cost) {
                        minEdge[j] = cost;
                    }
                }
            }
        }

        System.out.printf("%.2f", result);
    }
}