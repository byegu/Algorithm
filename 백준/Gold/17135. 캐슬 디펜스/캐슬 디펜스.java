import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    static int N; // 행
    static int M; // 열
    static int D; // 궁수 사거리 제한

    static int map[][];
    static int kill = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.println(kill);
    }

    static int[] archer(int r, int c, int[][] map) {
        int minDist = Integer.MAX_VALUE;
        int targetR = -1;
        int targetC = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] == 1) {

                    int dist = Math.abs(r - i) + Math.abs(c - j);

                    if (dist <= D) {

                        // 최소 거리 우선
                        if (dist < minDist) {
                            minDist = dist;
                            targetR = i;
                            targetC = j;
                        }
                        // 거리 같으면 왼쪽 우선
                        else if (dist == minDist && j < targetC) {
                            targetR = i;
                            targetC = j;
                        }
                    }
                }
            }
        }

        if (targetR == -1) return null;

        return new int[] {targetR, targetC};
    }

    static void move(int[][] map) {
        for (int i = N-1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                map[i][j] = map[i-1][j];
            }
        }

        for (int i = 0; i < M; i++) {
            map[0][i] = 0;
        }
    }

    static void simulate() {
        int count = 0;

        int[][] newMap = new int[N+1][M];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        for (int turn = 0; turn < N; turn++) {
            // 궁수 3명 타겟 저장
            ArrayList<int[]> targets = new ArrayList<>();

            for (int j = 0; j < M; j++) {
                if (newMap[N][j] == 3) {
                    int[] t = archer(N, j, newMap);
                    if (t != null) targets.add(t);
                }
            }

            // 적 제거
            for (int[] t : targets) {
                if (newMap[t[0]][t[1]] == 1) {
                    newMap[t[0]][t[1]] = 0;
                    count++;
                }
            }

            move(newMap);
        }

        kill = Math.max(kill, count);
    }

    static void comb(int depth, int start) {
        if (depth == 3) {
            simulate();
            return;
        }

        for (int i = start; i < M; i++) {
            map[N][i] = 3;
            comb(depth + 1, i + 1);
            map[N][i] = 0;
        }
    }
}