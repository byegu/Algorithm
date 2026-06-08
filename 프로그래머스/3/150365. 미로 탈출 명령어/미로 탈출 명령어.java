import java.util.*;

class Solution {
    class Point {
        int r;
        int c;
        String command;

        public Point(int r, int c, String command) {
            this.r = r;
            this.c = c;
            this.command = command;
        }
    }

    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        int dist = Math.abs(x - r) + Math.abs(y - c);

        if (dist > k) {
            return "impossible";
        }

        if ((k - dist) % 2 != 0) {
            return "impossible";
        }

        answer = bfs(n, m, x, y, r, c, k);

        return answer;
    }

    public String bfs(int n, int m, int start_r, int start_c, int end_r, int end_c, int k) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n + 1][m + 1][k + 1];
        q.offer(new Point(start_r, start_c, ""));
        visited[start_r][start_c][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            int r = p.r;
            int c = p.c;
            String command = p.command;

            int count = command.length();

            if (r == end_r && c == end_c && count == k) {
                return command;
            }

            if (count == k) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (!isIn(nr, nc, n, m)) {
                    continue;
                }

                int nextCount = count + 1;

                if (visited[nr][nc][nextCount]) {
                    continue;
                }

                int remain = k - nextCount;
                int dist = Math.abs(nr - end_r) + Math.abs(nc - end_c);

                if (dist > remain) {
                    continue;
                }

                if ((remain - dist) % 2 != 0) {
                    continue;
                }

                String nextCommand = command + dir[i];

                visited[nr][nc][nextCount] = true;
                q.offer(new Point(nr, nc, nextCommand));
            }
        }

        return "impossible";
    }

    public boolean isIn(int r, int c, int n, int m) {
        return r >= 1 && r <= n && c >= 1 && c <= m;
    }
}