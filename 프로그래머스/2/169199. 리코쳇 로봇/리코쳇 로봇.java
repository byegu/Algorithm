import java.util.*;

class Solution {

    class Point {
        int r;
        int c;
        int count;

        public Point(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    static boolean[][] visited;

    public int solution(String[] board) {
        return bfs(board);
    }

    public int bfs(String[] board) {

        int m = board.length;
        int n = board[0].length();

        Queue<Point> q = new ArrayDeque<>();
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i].charAt(j) == 'R') {
                    q.offer(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            int r = p.r;
            int c = p.c;
            int count = p.count;

            if (board[r].charAt(c) == 'G') {
                return count;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r;
                int nc = c;

                while (true) {
                    nr += dr[i];
                    nc += dc[i];

                    if (!isIn(nr, nc, m, n) || board[nr].charAt(nc) == 'D') {
                        nr -= dr[i];
                        nc -= dc[i];

                        break;
                    }
                }

                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                q.offer(new Point(nr, nc, count + 1));
            }
        }

        return -1;
    }

    public boolean isIn(int r, int c, int m, int n) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }
}