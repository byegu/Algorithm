import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        String[] colors = {"red", "orange", "yellow", "green", "blue", "purple"};
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < colors.length; i++) {
            map.put(colors[i], i);
        }

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = map.get(st.nextToken());
            int b = map.get(st.nextToken());

            int diff = Math.abs(a - b);

            if (diff == 0) {
                System.out.println("E");
            } else if (diff == 1 || diff == 5) {
                System.out.println("A");
            } else if (diff == 3) {
                System.out.println("C");
            } else {
                System.out.println("X");
            }
        }
    }
}