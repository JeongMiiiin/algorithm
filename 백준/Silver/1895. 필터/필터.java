import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		int[][] map = new int[R][C];
		//맵 세팅
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) map[i][j] = sc.nextInt();
		}
		int result = 0;
		int T = sc.nextInt();
		for(int i=0; i < R - 2; i++) {
			for(int j=0; j < C - 2; j++) {
				int[] temp = new int[9];
				int idx = 0;
				for(int r=i; r < i + 3; r++) {
					for(int c=j; c < j + 3; c++) temp[idx++] = map[r][c];
				}
				Arrays.sort(temp);
				if(temp[4] >= T) result++;
			}
		}
		System.out.println(result);
		sc.close();
	}
}