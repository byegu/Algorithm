import java.util.*;

class Solution {
    static class Point {
        int r;
        int c;
        int lever;
        int dist;

        Point(int r, int c, int lever, int dist) {
            this.r = r;
            this.c = c;
            this.lever = lever;
            this.dist = dist;
        }
    }

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int N, M;

    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        answer = bfs(maps);

        return answer;
    }

    public int bfs(String[] maps) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == 'S') {
                    q.offer(new Point(i, j, 0, 0));
                    visited[i][j][0] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            int lever = p.lever;
            int dist = p.dist;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (!isIn(nr, nc)) {
                    continue;
                }

                char ch = maps[nr].charAt(nc);

                if (ch == 'X') continue;

                int nextLever = lever;

                if (ch == 'L') nextLever = 1;

                if (!visited[nr][nc][nextLever]) {
                    visited[nr][nc][nextLever] = true;

                    if (ch == 'E' && nextLever == 1) {
                        return dist + 1;
                    }

                    q.offer(new Point(nr, nc, nextLever, dist + 1));
                }
            }
        }

        return -1;
    }

    public boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}