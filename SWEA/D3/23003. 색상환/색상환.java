import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			String A, B;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			A = st.nextToken();
			B = st.nextToken();
			
			if (A.equals(B)) {
				System.out.println("E");
			} else if (A.equals("red")) {
				if (B.equals("orange") || B.equals("purple")) {
					System.out.println("A");
				} else if (B.equals("green")) {
					System.out.println("C");
				} else {
					System.out.println("X");
				}
			} else if (A.equals("orange")) {
				if (B.equals("red") || B.equals("yellow")) {
					System.out.println("A");
				} else if (B.equals("blue")) {
					System.out.println("C");
				} else {
					System.out.println("X");
				}
			} else if (A.equals("yellow")) {
				if (B.equals("orange") || B.equals("green")) {
					System.out.println("A");
				} else if (B.equals("purple")) {
					System.out.println("C");
				} else {
					System.out.println("X");
				}
			} else if (A.equals("green")) {
				if (B.equals("yellow") || B.equals("blue")) {
					System.out.println("A");
				} else if (B.equals("red")) {
					System.out.println("C");
				} else {
					System.out.println("X");
				}
			} else if (A.equals("blue")) {
				if (B.equals("green") || B.equals("purple")) {
					System.out.println("A");
				} else if (B.equals("orange")) {
					System.out.println("C");
				} else {
					System.out.println("X");
				}
			} else if (A.equals("purple")) {
				if (B.equals("blue") || B.equals("red")) {
					System.out.println("A");
				} else if (B.equals("yellow")) {
					System.out.println("C");
				} else {
					System.out.println("X");
				}
			}
		}
	}
}
