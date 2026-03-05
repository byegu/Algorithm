import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class MountainRescueRobot {
    static int T;
    static int N;
    static int map[][];
    static int dist[][];
    static int result;

    static int[] dr = { 0, 0, -1, 1 };
    static int[] dc = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dist = new int[N][N];
            result = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra();
            System.out.println();
            System.out.println("#" + t + " " + dist[N-1][N-1]);
        }
    }
    

    static void dijkstra() {
        Queue<int[]> q = new ArrayDeque<>();
        dist[0][0] = 0;
        q.offer(new int[] {0, 0});
        
        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int r = arr[0];
            int c = arr[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int fuel = 0;
                
                if (!isIn(nr, nc)) {
                    continue;
                }
                
                if (map[r][c] == map[nr][nc]) {
                    fuel = 1;
                }  else if (map[r][c] < map[nr][nc]) {
                    fuel = (map[nr][nc] - map[r][c]) * 2;
                }
                
                if (dist[r][c] + fuel < dist[nr][nc]) {
                    q.offer(new int[] {nr, nc});
                    dist[nr][nc] = dist[r][c] + fuel;
                }
                
            }
        }
        

    }
    
    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}