import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] flag = new boolean[10];
            int count = 0;

            int k = 0;
            int current = 0;

            while (count < 10) {
                k++;
                current = k * N;

                char[] c = String.valueOf(current).toCharArray();
                for (char ch : c) {
                    int digit = ch - '0';
                    if (!flag[digit]) {
                        flag[digit] = true;
                        count++;
                    }
                }
            }
            System.out.println("#" + t + " " + current);
        }
    }
}