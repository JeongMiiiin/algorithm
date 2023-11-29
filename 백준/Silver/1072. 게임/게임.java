import java.util.Scanner;

/*
 * 백준 1072 - 게임
 * 게임 횟수 : X
 * 이긴 게임 : Y (Z%)
 * Z는 형택이의 승률이고, 소수점은 버린다.
 * X와 Y가 주어졌을 때, 형택이가 게임을 최소 몇 번 더 해야 Z가 변하는지 구하라.
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long X = sc.nextLong();
		long Y = sc.nextLong();
		long Z = Y * 100 / X;
		long left = 1, right = (int) X;
		if(Z < 99) {
			while(left < right) {
				long mid = (left + right) / 2;
				long temp = (Y + mid) * 100 / (X + mid);
				if(Z >= temp) left = mid + 1;
				else right = mid;
			}
		} else right = -1;
		System.out.println(right);
		sc.close();
	}
}