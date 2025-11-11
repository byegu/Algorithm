/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			String s = br.readLine();
			int n = s.length();
			boolean valid = true;

			int first[] = new int[10];
			boolean appeared[] = new boolean[10];
			int count[] = new int[10];

			if (s.charAt(0) - '0' == 0)
				valid = false;

			for (int i = 0; i < n; i++) {
				int d = s.charAt(i) - '0';
				count[d]++;
				
				if(count[d] > 2) {
					valid = false;
					break;
				}

				if (!appeared[d]) {
					first[d] = i;
					appeared[d] = true;
				} else {
					int dist = i - first[d] - 1;

					if (dist != d) {
						valid = false;
						break;
					}
					appeared[d] = false;
				}

			}

			for (int i = 0; i < 10; i++) {
				if (appeared[i])
					valid = false;
			}

			System.out.println(valid ? "yes" : "no");
		}
	}
}
