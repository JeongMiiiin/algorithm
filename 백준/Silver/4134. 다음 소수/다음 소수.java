import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			long N = Long.parseLong(sc.nextLine());
			while(!check(N)) N++;
			sb.append(N + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static boolean check(long target) {
		if(target == 0 || target == 1) return false;
		for(long i=2; i <= Math.sqrt(target); i++) if(target % i == 0) return false;
		return true;
	}
}