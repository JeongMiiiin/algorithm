import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = 10;
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			for(int i=0; i < N; i++) arr[i] = sc.nextInt();
			int result = 0;
			for(int i=2; i < N - 2; i++) {
				int lm = Math.max(arr[i - 1], arr[i - 2]);
				int rm = Math.max(arr[i + 1], arr[i + 2]);
				if(arr[i] > lm && arr[i] > rm) result += arr[i] - Math.max(lm, rm);
			}
			
			
			sb.append("#"+ t + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}