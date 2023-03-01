import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] payInfo = new int[N];
		for(int i=0; i < N; i++)
			payInfo[i] = sc.nextInt();
		
		int cnt = 0;
		int idx = N - 1;
		while(M > 0) {
			int share = M / payInfo[idx];
			if(share > 0) {
				cnt += share;
				M -= share * payInfo[idx];
			}
			idx--;
		}
		System.out.println(cnt);
	}
}
