import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] arr = new int[N][M];
		for(int i=0; i < N; i++) for(int j=0; j < M; j++) arr[i][j] = sc.nextInt();
		int K = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int z=0; z < K; z++) {
			int i = sc.nextInt() - 1;
			int j = sc.nextInt() - 1;
			int x = sc.nextInt();
			int y = sc.nextInt();
			int sum = 0;
			for(int a=i; a < x; a++) for(int b=j; b < y; b++) sum += arr[a][b];
			
			sb.append(sum + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}