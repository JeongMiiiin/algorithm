import java.util.Scanner;

/*
 * 백준 1654번 랜선 자르기
 * K : 현재 가지고 있는 랜선의 개수
 * N : 최종적으로 만들어야 하는 랜선의 개수
 * K번째 줄까지 : 현재 가지고 있는 랜선들의 길이
*/
public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int N = sc.nextInt();
		int[] inputs = new int[K];
		long max = Integer.MIN_VALUE;
		for(int i=0; i < K; i++) {
			int n = sc.nextInt();
			inputs[i] = n;
			max = Math.max(max, n);
		}
		
		max++;
		
		long min = 0;
		long mid = 0;
		// min = 0, max 는 입력받은 LAN선 중 가장 긴 길이를 갖는다.
		while (min < max) { 
		 
			// 범위 내에서 중간 길이를 구한다.
			mid = (max + min) / 2;
		 
			long count = 0;
		 
			// 구해진 중간 길이로 잘라서 총 몇 개가 만들어지는지를 구한다.
			for (int i = 0; i < inputs.length; i++)
				count += (inputs[i] / mid);
			
			if(count < N) {
				max = mid;
			}
			else {
				min = mid + 1;
			}
			
		}
		
		System.out.println(min - 1);
	}
}
