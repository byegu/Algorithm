import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] sudoku = new int[9][9];
    static boolean[][] check_row = new boolean[9][10];
    static boolean[][] check_col = new boolean[9][10];
    static boolean[][] check_area = new boolean[9][10];
    static List<Point> list = new ArrayList<>();

    static class Point {
        int r, c;
        public Point(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                sudoku[i][j] = num;
                if (num == 0) {
                    list.add(new Point(i, j));
                } else {
                    check_row[i][num] = true;
                    check_col[j][num] = true;
                    check_area[getArea(i, j)][num] = true;
                }
            }
        }

        dfs(0);
    }

    static int getArea(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    static void dfs(int depth) {
        if (depth == list.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudoku[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
            System.exit(0);
        }

        Point p = list.get(depth);
        int r = p.r;
        int c = p.c;
        int area = getArea(r, c);

        for (int i = 1; i <= 9; i++) {
            if (!check_row[r][i] && !check_col[c][i] && !check_area[area][i]) {
                check_row[r][i] = true;
                check_col[c][i] = true;
                check_area[area][i] = true;
                sudoku[r][c] = i;

                dfs(depth + 1);

                check_row[r][i] = false;
                check_col[c][i] = false;
                check_area[area][i] = false;
                sudoku[r][c] = 0;
            }
        }
    }
}