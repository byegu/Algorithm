import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sc = Integer.parseInt(br.readLine());
        int[] switc = new int[sc + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= sc; i++) {
            switc[i] = Integer.parseInt(st.nextToken());
        }

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            int gender = Integer.parseInt(st2.nextToken());
            int num = Integer.parseInt(st2.nextToken());

            if (gender == 1) {
                for (int j = num; j <= sc; j += num) {
                    switc[j] = 1 - switc[j];
                }
            }
            
            else {
                int left = num;
                int right = num;

                while (left - 1 >= 1 && right + 1 <= sc
                        && switc[left - 1] == switc[right + 1]) {
                    left--;
                    right++;
                }
                
                for (int k = left; k <= right; k++) {
                    switc[k] = 1 - switc[k];
                }
            }
        }

        for (int i = 1; i <= sc; i++) {
            System.out.print(switc[i] + " ");
            if (i % 20 == 0) System.out.println();
        }
    }
}
