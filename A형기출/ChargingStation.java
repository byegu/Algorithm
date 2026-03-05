import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ChargingStation {

	static int N;

	static class Point {
		int r;
		int c;
		int dist;


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}

		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static List<Point> house, pos;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			house = new ArrayList<>();
			pos = new ArrayList<>();
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken()) + 15;
				int c = Integer.parseInt(st.nextToken()) + 15;
				int range = Integer.parseInt(st.nextToken());
				house.add(new Point(r, c, range));
			}
			
			for (int i = 1; i <= 2; i++) {
				setCharger(0, i);
				if (answer != Integer.MAX_VALUE) break;
			}
			
			if (answer == Integer.MAX_VALUE) answer = -1;
			
			System.out.println("#" + t + " " + answer);
		}

	}
	
	static void setCharger(int count, int end) {
		// end는 허용된 충전소 개수
		if (count == end) {
			if (!check()) {
				return;
			}
			answer = Math.min(answer, getDistanceSum());
			return;
		}
		
		int r = 0;
		int c = 0;
		// 충전소 설치 한도가 2개인데 하나를 지은 경우 이전에 끝난 좌표부터 설치 재개
		if (count == 1 && end == 2) {
			r = pos.get(0).r;
			c = pos.get(0).c;
		}
		for (int i = r; i <= 30; i++) {
			for (int j = c; j <= 30; j++) {
				Point p = new Point(i, j);
				if (pos.contains(p)) continue;
				pos.add(p);
				setCharger(count + 1, end);
				pos.remove(count);
			}
		}
	}

	// 모든 충전소가 집에 연결되어 있는지 체크
	static boolean check() {
		boolean[] connect = new boolean[N];
		for (Point p : pos) {
			for (int i = 0; i < N; i++) {
				Point h = house.get(i);
				int dist = getDistance(p.r, p.c, h.r, h.c);
				// 충전소를 설치한 장소가 집의 좌표와 같거나 집에서 사용가능한 범위를 넘어선 경우 스킵
				if (p.r == h.r && p.c == h.c || dist > h.dist) continue;
				
				connect[i] = true;
			}
		}
		
		for (int i = 0; i < N; i++) {
			// 연결되지 않은 집이 있는 경우 false
			if (!connect[i]) {
				return false;
			}
		}
		return true;
	}
	
	static int getDistanceSum() {
		int sum = 0;
		int[] distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		for (Point p : pos) {
			for (int i = 0; i < N; i++) {
				Point h = house.get(i);
				int dist = getDistance(p.r, p.c, h.r, h.c);
				distance[i] = Math.min(dist,  distance[i]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			sum += distance[i];
		} 
		return sum;
	}
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}
