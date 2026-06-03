class Solution {
    static boolean[][][][] visited;

    public int solution(String dirs) {
        int answer = 0;

        visited = new boolean[11][11][11][11];

        int r = 5;
        int c = 5;

        for (int i = 0; i < dirs.length(); i++) {
            char command = dirs.charAt(i);

            int nr = r;
            int nc = c;

            if (command == 'U') {
                nc++;
            } else if (command == 'D') {
                nc--;
            } else if (command == 'R') {
                nr++;
            } else if (command == 'L') {
                nr--;
            }

            if (!isIn(nr, nc)) {
                continue;
            }

            if (!visited[r][c][nr][nc]) {
                visited[r][c][nr][nc] = true;
                visited[nr][nc][r][c] = true;
                answer++;
            }

            r = nr;
            c = nc;
        }

        return answer;
    }
    
    public static boolean isIn(int r, int c) {
        return r >= 0 && r < 11 && c >= 0 && c < 11;
    }
}