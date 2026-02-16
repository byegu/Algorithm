import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] population;
    static ArrayList<Integer>[] graph;
    static int[] group;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        graph = new ArrayList[N + 1];
        group = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                graph[i].add(to);
            }
        }

        subset(1);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void subset(int depth) {
        if (depth == N + 1) {
            check();
            return;
        }

        group[depth] = 0;
        subset(depth + 1);

        group[depth] = 1;
        subset(depth + 1);
    }

    static void check() {
        visited = new boolean[N + 1];

        int a = -1, b = -1;
        for (int i = 1; i <= N; i++) {
            if (group[i] == 0 && a == -1) a = i;
            if (group[i] == 1 && b == -1) b = i;
        }

        if (a == -1 || b == -1) return;

        int sumA = bfs(a, 0);
        int sumB = bfs(b, 1);

        for (int i = 1; i <= N; i++)
            if (!visited[i]) return;

        answer = Math.min(answer, Math.abs(sumA - sumB));
    }

    static int bfs(int start, int team) {

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        int sum = population[start];

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (!visited[next] && group[next] == team) {
                    visited[next] = true;
                    q.offer(next);
                    sum += population[next];
                }
            }
        }
        return sum;
    }
}
