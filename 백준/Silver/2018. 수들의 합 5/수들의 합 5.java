import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int result = 0;
		int start = 1, max = N % 2 == 1 ? N / 2 + 1 : N / 2;
		while(start <= max) {
			int temp = start;
			int add = 1;
			while(temp < N) temp += start + add++;
			if(temp == N) result++;
				
			start++;
		}
		
		if(N > 1) result++;
		System.out.println(result);
		sc.close();
	}
}