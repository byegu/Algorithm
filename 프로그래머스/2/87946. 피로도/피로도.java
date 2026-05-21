import java.util.*;

class Solution {
    static int N, M;
    static int max = 0;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        N = dungeons.length;
        M = dungeons[0].length;
        visited = new boolean[N];
        int[] list = new int[N];
        Arrays.fill(list, -1);
        
        dfs(0, k, dungeons, new int[N]);
        answer = max;
        return answer;
    }
    
    public void dfs(int depth, int k, int[][] dungeons, int[] list) {
        if (depth == N) {
            int result = explore(list, k, dungeons);
            max = Math.max(result, max);
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list[depth] = i;
                dfs(depth+1, k, dungeons, list);
                visited[i] = false;
            }
        }
        return;
    }
    
    public int explore(int[] list, int k, int[][] dungeons) {
        int result = 0;
        int fatigue = k;
        for (int i = 0; i < N; i++) {
            int idx = list[i];
            if (fatigue >= dungeons[idx][0]) {
                fatigue -= dungeons[idx][1];
                result++;
            }
        }
        return result;
    }
}