import java.util.Scanner;

/*
 * 백준 12969 - ABC
 * 
 * 주어지는 값
 * N : 문자열 S의 길이
 * K : 0 <= i < j < N 이면서 S[i] < S[j]를 만족하는 (i,j) 쌍이 K개가 있다.
 * 
 * 첫째 줄에 N과 K가 주어지고, 조건을 만족하는 문자열 S를 출력한다.
 * 가능한 S가 여러가지라면, 아무거나 출력해도 됨.
 * 만약 불가능할 시 -1 출력.
 * 
 * DP로 해결.. 해보겠음
 * 
 * 4차원 배열
 * dp = [a][b][n][p] -> a의 개수, b의 개수, 현재 문자열의 길이, 쌍의 개수
 * 
 * 방문처리를 통해 백트래킹
 * 
*/
public class Main {
	static boolean flag = false;
	static int N, K;
	static boolean[][][][] dp;
	static StringBuilder ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		dp = new boolean[31][31][31][436];
		
		find(0, 0, 0, 0, new StringBuilder());
		
		//출력
		if(!flag) System.out.println(-1);
		else System.out.println(ans.toString());
		
		sc.close();
	}
	
	private static void find(int a, int b, int n, int p, StringBuilder sb) {
		if(flag) return;
		
		if(dp[a][b][n][p]) return; //이미 방문했으면 리턴
		
		dp[a][b][n][p] = true;
		
		if(n == N) {
			if(p == K) {
				flag = true;
				ans = new StringBuilder(sb.toString());
			}
			return;
		}
		
		//a를 추가
		find(a + 1, b, n + 1, p, new StringBuilder(sb.toString() + "A"));
		
		//b를 추가
		find(a, b + 1, n + 1, p + a, new StringBuilder(sb.toString() + "B"));
		
		//c를 추가
		find(a, b, n + 1, p + a + b, new StringBuilder(sb.toString() + "C"));
				
	}
}