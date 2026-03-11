import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N; // 구역 크기
    static int K; // 군집 수
    static int M; // 격리 시간
    static int[][] map;

    // 상 하 좌 우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static int[] opposite = { 1, 0, 3, 2 };

    static class Microbes {
        int r;
        int c;
        int count;
        int dir;
        int max;

        public Microbes(int r, int c, int count, int dir) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.dir = dir;
            this.max = count;
        }
    }
    static ArrayList<Microbes> cluster;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cluster = new ArrayList<>();
            cluster.add(new Microbes(-1, -1, 0, 0));

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                cluster.add(new Microbes(r, c, count, dir));
            }

            for (int time = 0; time < M; time++) {
                map = new int[N][N];
                ArrayList<Microbes> merge = new ArrayList<>();
                merge.add(new Microbes(-1, -1, 0, 0));
                for (int i = 1; i < cluster.size(); i++) {
                    Microbes m = cluster.get(i);
                    int nr = m.r + dr[m.dir];
                    int nc = m.c + dc[m.dir];
                    int count = m.count;
                    int dir = m.dir;

                    if (!isIn(nr, nc)) {
                        count /= 2;
                        dir = opposite[dir];
                    }

                    if (count == 0)
                        continue;

                    if (map[nr][nc] == 0) {
                        merge.add(new Microbes(nr, nc, count, dir));
                        map[nr][nc] = merge.size() - 1;
                    } 
                    else {
                        int idx = map[nr][nc];
                        Microbes exist = merge.get(idx);

                        if (count > exist.max) {
                            exist.max = count;
                            exist.dir = dir;
                        }
                        exist.count += count;
                    }
                }
                cluster = merge;
            }

            int result = 0;
            for (int i = 1; i < cluster.size(); i++) {
                result += cluster.get(i).count;
            }

            System.out.println("#" + t + " " + result);
        }
    }

    static boolean isIn(int r, int c) {
        return r >= 1 && r < N - 1 && c >= 1 && c < N - 1;
    }
}