import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		long result = A * B;
		
		long a, b, tmp;
		if(A > B) {
			a = A;
			b = B;
		} else {
			a = B;
			b = A;
		}
		while(a % b != 0) {
			tmp = a % b;
			a = b;
			b = tmp;
		}
		
		System.out.println(result / b);
		sc.close();
	}
}