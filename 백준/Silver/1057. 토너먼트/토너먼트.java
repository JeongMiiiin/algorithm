import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int maxRound = 0;
		while(N / 2 > 0) {
			N /= 2;
			maxRound++;
		}
		maxRound++;
		
		int result = 0;
		while(result <= maxRound) {
			result++; //라운드 개장
			if((a % 2 == 1 && a + 1 == b) || (b % 2 == 1 && b + 1 == a)) break; //둘이 라운드에 만났을 때
			a = a % 2 == 0 ? a / 2 : (a + 1) / 2;
			b = b % 2 == 0 ? b / 2 : (b + 1) / 2;
		}
		
		System.out.println(result <= maxRound ? result : -1);
		sc.close();
	}
}