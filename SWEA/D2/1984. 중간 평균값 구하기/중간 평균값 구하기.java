import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[10];

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int sum = 0;

            for (int i = 0; i < 10; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum += arr[i];
                max = Math.max(max, arr[i]);
                min = Math.min(min, arr[i]);
            }

            sum -= max;
            sum -= min;

            double avg = sum / 8.0;

            int result = (int)Math.round(avg);

            System.out.println("#" + t + " " + result);
        }
    }
}