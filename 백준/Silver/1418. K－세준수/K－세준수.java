import java.util.Scanner;

public class Main {
	static int K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		K = Integer.parseInt(sc.nextLine());
		int result = 0;
		for(int i=1; i <= N; i++) if(calc(i)) result++;
		
		System.out.println(result);
		sc.close();
	}
	
	private static boolean calc(int target) {
		int i = 2;
		int j = 1;
		while(target > 1) {
			if(target % i == 0) {
				target /= i;
				j = Math.max(i, j);
			} else i++;
			if(i > K) return false;
		}
		
		return true;
	}
	
}