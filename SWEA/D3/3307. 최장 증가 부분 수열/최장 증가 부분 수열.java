import java.util.Scanner;

/*
 * SWEA 3307 - 최장증가부분수열
 * 
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 주어지는 배열의 크기
 * 2번째 줄 : 배열 값들
 * 
 * 들어오는 수열에서 증가하는 부분수열의 최장길이를 구하라
*/
public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t <= T; t++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			int[] LIS = new int[N];
			
			for(int i=0; i < N; i++) arr[i] = sc.nextInt();
			
			int ans = 0;
			for(int i=0; i < N; i++) {
				LIS[i] = 1;
				for(int j=0; j < i; j++) if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) LIS[i] = LIS[j] + 1;
				
				if(ans < LIS[i]) ans = LIS[i];
			}
			
			System.out.println("#" + t + " " + ans);
		}
		
		sc.close();
	}
}