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
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	private static int result = -1; //최종 제출할 값
	private static int[][] map = new int[100][100]; //map을 담을 배열
	private static boolean[][] visited = new boolean[100][100]; //지나온 곳인지의 상태를 담는 배열
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int t = 1; t <= T; t++) {
			//입력
			int testCase = sc.nextInt(); //문제 번호
			
			//map 값을 담음
			for(int r=0; r < 100; r++) {
				for(int c=0; c < 100; c++)
					map[r][c] = sc.nextInt();
			}
			
			//목적지의 Y좌표 구하기
			int X = -1;
			for(int c=0; c < 100; c++) {
				if(map[99][c] == 2) {
					X = c;
					break;
				}
			}
			
			//처리
			solve(99,X);
			
			//출력
			System.out.println("#" + testCase + " " + result);
			
		}
	}
	
	private static void solve(int r, int c) {
		if(r == 0) {
			result = c; //최초 출발지 y좌표 result에 할당
			return;
		} 
		//좌 우 상
		int[] dr1 = { 0,0,-1 };
		int[] dc1 = { -1,1,0 };
		
		for(int i=0; i < 3; i++) { //좌 우 상 돌며 체크
			int nr = r + dr1[i];
			int nc = c + dc1[i];
			// 배열의 크기를 벗어나면 체크 안하게 처리 (좌우만 체크)
			if (nc > -1 && nc < 100) {
				if(map[nr][nc] == 1 && !visited[nr][nc]) {
					r = nr;
					c = nc;
					visited[nr][nc] = true;
					solve(r,c);
					visited[nr][nc] = false;
					break;
				}
			}
		}
	}
}