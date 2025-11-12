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
		
		for(int i = 0; i < T; i++) {
			String str = br.readLine();
			
			char[] S = new char[str.length()];
			
			S = str.toCharArray();
			int distL = 0;
			int distR = 0;
			
			int now = 0;
			for (char c : S) {
				if(c == 'L') {
					now--;
				} else if (c == 'R') {
					now++;
				} else if (c == '?') {
					now--;
				}
			
				if(Math.abs(now) > distL) {
					distL = Math.abs(now);
				}	
			}
			
			now = 0;
			for (char c : S) {
				if(c == 'L') {
					now--;
				} else if (c == 'R') {
					now++;
				} else if (c == '?') {
					now++;
				}
			
				if(Math.abs(now) > distR) {
					distR = Math.abs(now);
				}	
			}
			
			System.out.println(Math.max(distR, distL));
		}
	}

}