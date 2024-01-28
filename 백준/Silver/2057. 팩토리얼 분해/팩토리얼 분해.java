import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		if(N == 0) {
			System.out.println("NO");
			sc.close();
			return;
		}
		
		long[] factorial = new long[20];
        factorial[0] = 1;
        factorial[1] = 1;

        for (int i = 2; i < 20; i++) {
            factorial[i] = i * factorial[i - 1];
        }
        
        boolean flag = false;
        for (int i = 19; i >= 0; i--) {
            if (N >= factorial[i]) {
                N -= factorial[i];
            }
            if (N == 0) {
                flag = true;
                break;
            }
        }

        System.out.println(flag ? "YES" : "NO");
		
		sc.close();
	}
}