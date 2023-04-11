import java.util.Scanner;

/*
 * 백준 11653 - 소인수 분해
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		for(int i=2; i <= (int) Math.sqrt(N); i++) {
			while(N % i == 0) {
				System.out.println(i);
				N /= i;
			}
		}
		
		if(N != 1) System.out.println(N);
		
		sc.close();
	}
}