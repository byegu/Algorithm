class Solution {
    int[][] info;
    int n;
    int m;
    int answer = Integer.MAX_VALUE;

    boolean[][][] visited;

    public int solution(int[][] info, int n, int m) {
        this.info = info;
        this.n = n;
        this.m = m;

        visited = new boolean[info.length + 1][n][m];

        dfs(0, 0, 0);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public void dfs(int idx, int a, int b) {
        if (a >= n || b >= m) {
            return;
        }

        if (a >= answer) {
            return;
        }

        if (visited[idx][a][b]) {
            return;
        }

        visited[idx][a][b] = true;

        if (idx == info.length) {
            answer = Math.min(answer, a);
            return;
        }

        if (b + info[idx][1] < m) {
            dfs(idx + 1, a, b + info[idx][1]);
        }

        if (a + info[idx][0] < n) {
            dfs(idx + 1, a + info[idx][0], b);
        }
    }
}