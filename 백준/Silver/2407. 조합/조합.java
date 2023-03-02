import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static int N, R;
	static long ans;
	static int[] inputs, numbers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		BigInteger n1 = BigInteger.ONE;
        BigInteger n2 = BigInteger.ONE;

        for (int i = 0; i < R; i++) {
            n1 = n1.multiply(new BigInteger(String.valueOf(N - i)));
            n2 = n2.multiply(new BigInteger(String.valueOf(i + 1)));
        }

        BigInteger ans = n1.divide(n2);
        System.out.println(ans);
        sc.close();
	}
}
