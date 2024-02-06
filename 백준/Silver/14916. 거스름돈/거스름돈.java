import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result = Integer.MAX_VALUE;
		int idx = 0;
		while(idx <= N / 5) {
			int temp = N - idx * 5;
			if(temp % 2 == 0) result = Math.min(result, idx + (temp / 2));
			
			idx++;
		}
		
		if(result == Integer.MAX_VALUE) result = -1;
		System.out.println(result);
		sc.close();
	}
}