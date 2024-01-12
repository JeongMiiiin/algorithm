import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[101][101];
		int N = sc.nextInt();
		int M = sc.nextInt();
		for(int i=0; i < N; i++) {
			int startR = sc.nextInt();
			int startC = sc.nextInt();
			int endR = sc.nextInt();
			int endC = sc.nextInt();
			for(int r=startR; r <= endR; r++) for(int c=startC; c <= endC; c++) map[r][c]++;
		}
		int result = 0;
		for(int i=1; i <= 100; i++) for(int j=1; j <= 100; j++) if(map[i][j] > M) result++;
		System.out.println(result);
		sc.close();
	}
}