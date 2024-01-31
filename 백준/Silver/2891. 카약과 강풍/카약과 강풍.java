import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int S = sc.nextInt();
		int R = sc.nextInt();
		boolean[] check = new boolean[N + 1];
		Arrays.fill(check, true);
		boolean[] remain = new boolean[N + 1];
		for(int i=0; i < S; i++) check[sc.nextInt()] = false;
		for(int i=0; i < R; i++) {
			int target = sc.nextInt();
			if(!check[target]) check[target] = true;
			else remain[target] = true;
		} 
		
		int result = 0;
		for(int i=1; i <= N; i++) {
			if(!check[i]) {
				if(i > 1 && remain[i - 1]) remain[i - 1] = false;
				else if(i < N && remain[i + 1]) remain[i + 1] = false;
				else result++;
			}
		}
		System.out.println(result);
		sc.close();
	}
}