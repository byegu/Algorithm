import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dist;
    static int bamboo;

    static int dr[] = { 1, -1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        traversal();
        System.out.println(bamboo);
    }

    static int dfs(int r, int c) {
        if (dist[r][c] != 0) {
            return dist[r][c];
        }

        dist[r][c] = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (!isIn(nr, nc)) continue;

            if (map[r][c] < map[nr][nc]) {
                dist[r][c] = Math.max(dist[r][c], dfs(nr, nc) + 1);
            }
        }

        return dist[r][c];
    }

    static void traversal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bamboo = Math.max(bamboo, dfs(i, j));
            }
        }
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}