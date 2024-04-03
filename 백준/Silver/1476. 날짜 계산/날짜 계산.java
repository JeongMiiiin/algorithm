import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int result = E;
		while(true) {
			int s = result % 28 != 0 ? result % 28 : 28;
			int m = result % 19 != 0 ? result % 19 : 19;
			if(s == S && m == M) break;
			result += 15;
		}
		
		System.out.println(result);
		sc.close();
	}
}