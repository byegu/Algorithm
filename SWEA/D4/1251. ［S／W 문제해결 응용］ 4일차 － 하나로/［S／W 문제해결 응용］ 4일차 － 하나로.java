import java.io.*;
import java.util.*;

public class Solution {

    static int T, N;
    static long[] x, y;
    static double E;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){

            N = Integer.parseInt(br.readLine());

            x = new long[N];
            y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) x[i] = Long.parseLong(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) y[i] = Long.parseLong(st.nextToken());

            E = Double.parseDouble(br.readLine());

            sb.append("#").append(t).append(" ").append(Math.round(prim())).append("\n");
        }

        System.out.print(sb);
    }

    static double prim(){

        boolean[] visited = new boolean[N];
        double[] minEdge = new double[N];

        Arrays.fill(minEdge, Double.MAX_VALUE);
        minEdge[0] = 0;

        double result = 0;

        for(int i=0;i<N;i++){

            double min = Double.MAX_VALUE;
            int minVertex = -1;

            for(int j=0;j<N;j++){
                if(!visited[j] && minEdge[j] < min){
                    min = minEdge[j];
                    minVertex = j;
                }
            }

            visited[minVertex] = true;
            result += min;

            for(int j=0;j<N;j++){
                if(!visited[j]){
                    long dx = x[minVertex] - x[j];
                    long dy = y[minVertex] - y[j];

                    double cost = E * (dx*dx + dy*dy);

                    if(minEdge[j] > cost){
                        minEdge[j] = cost;
                    }
                }
            }
        }

        return result;
    }
}