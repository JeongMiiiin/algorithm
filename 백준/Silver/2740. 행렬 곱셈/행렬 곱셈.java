import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] A = new int[N][M];
		for(int i=0; i < N; i++) for(int j=0; j < M; j++) A[i][j] = sc.nextInt();
		M = sc.nextInt();
		int K = sc.nextInt();
		int[][] B = new int[M][K];
		for(int i=0; i < M; i++) for(int j=0; j < K; j++) B[i][j] = sc.nextInt();
		int[][] result = new int[N][K];
		for(int i=0; i < N; i++) {
			for(int j=0; j < K; j++) {
				int target = 0;
				for(int ni = 0; ni < M; ni++) target += A[i][ni] * B[ni][j];
				result[i][j] = target;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) {
			for(int j=0; j < K; j++) sb.append(result[i][j] + " ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}