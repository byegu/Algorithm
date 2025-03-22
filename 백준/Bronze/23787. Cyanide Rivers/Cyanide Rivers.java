import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int n = input.length();
        int[] tower = new int[n];
        boolean[] certified = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            tower[i] = input.charAt(i) - '0';
            if (tower[i] == 1) {
                queue.offer(i);
                certified[i] = true;
            }
        }

        System.out.print(bfs(tower, certified, queue, n));
    }


    static int bfs(int[] tower, boolean[] certified, Queue<Integer> queue, int n) {
        int days = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean updated = false;

            for (int i = 0; i < size; i++) {
                int node = queue.poll();

                if (node - 1 >= 0 && tower[node - 1] == 0 && !certified[node - 1]) {
                    queue.offer(node - 1);
                    certified[node - 1] = true;
                    updated = true;
                }

                if (node + 1 < n && tower[node + 1] == 0 && !certified[node + 1]) {
                    queue.offer(node + 1);
                    certified[node + 1] = true;
                    updated = true;
                }
            }

            if(updated) days++;
        }
        return days;
    }
}