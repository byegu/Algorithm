import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            char[][] grid = new char[H][W];

            for(int h = 0; h < H; h++) {
                String line = br.readLine();
                grid[h] = line.toCharArray();
            }

            int cnt = 0;

            for(int h = 0; h < H; h++) {
                int flag = 0;
                for(int w = 0; w < W; w++) {
                    if(grid[h][w] == '.') flag = 1;
                }
                if(flag == 1) continue;
                cnt++;
            }

            for(int w = 0; w < W; w++) {
                int flag = 0;
                for(int h = 0; h < H; h++) {
                    if(grid[h][w] == '.') flag = 1;
                }
                if(flag == 1) continue;
                cnt++;
            }

            if(cnt == H+W) {
                cnt = Math.min(H, W);
            }

            System.out.println(cnt);

        }

    }

}
