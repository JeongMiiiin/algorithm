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
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	/*
	 * SWEA 2805 - 농작물 수확하기
	 * 농장의 크기는 항상 홀수
	 * 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태
	 * T : 테스트 케이스 수
	 * N : 농장의 크기
	 * 이후 : 농작물의 가치
	*/
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			//입력
			int N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			//값 담기
			for(int r=0; r < N; r++) {
				String st = br.readLine();
				for(int c=0; c < N; c++)
					map[r][c] = Integer.parseInt(st.substring(c,c+1));
			}
			
			int sum = 0;
			boolean isUp = true;
			for(int row = 0, col = Math.round(N / 2); row < N; row++) {
				if(row == Math.round(N / 2)) isUp = false;
				for(int j = col; j < N - col; j++) {
					sum += map[row][j];
				}
				
				if(isUp) {
					col--;
				} else {
					col++;
				}
			}
			
			
			bw.write("#" + t + " " + sum + "\n");
			
		}
		bw.close();
	}
}
