import java.io.*;
import java.util.*;

public class AlgoStock {

	static int initial, monthly, stockNum, monthNum, curMoney;
	static int[][] stockPrice;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			initial = Integer.parseInt(st.nextToken());
			curMoney = initial;
			monthly = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			stockNum = Integer.parseInt(st.nextToken());
			monthNum = Integer.parseInt(st.nextToken());
			stockPrice = new int[stockNum][monthNum + 1];
			for (int i = 0; i < stockNum; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < monthNum + 1; j++) {
					stockPrice[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < monthNum; i++) {
				getProfit(i);
			}

			// 투자원금 계산
			int seed = initial + monthly * monthNum;
			sb.append("#").append(tc).append(" ").append(curMoney - seed).append("\n");
		}
		System.out.println(sb);
	}

	private static void getProfit(int month) {
		int[] curStockPrice = new int[stockNum];
		int[] nextStockPrice = new int[stockNum];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < stockNum; i++) {
			curStockPrice[i] = stockPrice[i][month];
			nextStockPrice[i] = stockPrice[i][month + 1];
			min = Math.min(min, curStockPrice[i]);
		}
		int[] profit = new int[curMoney + 1];
		// 가장 저렴한 주식 구매가 ~ 현재 보유 투자금까지 계산
		for (int i = min; i <= curMoney; i++) {
			// 각 주식 구매 시 수익금 비교 후 최댓값을 저장
			for (int j = 0; j < stockNum; j++) {
				if (nextStockPrice[j] - curStockPrice[j] <= 0)
					continue;
				if (i - curStockPrice[j] < 0)
					continue;
				profit[i] = Math.max(profit[i], profit[i - curStockPrice[j]] + nextStockPrice[j] - curStockPrice[j]);
			}
		}
		curMoney += profit[curMoney] + monthly;
	}

}
