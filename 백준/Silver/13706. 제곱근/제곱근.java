import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger N = new BigInteger(sc.next());
		BigInteger left = new BigInteger("1"), right = N, mid;
		while(true) {
			mid = left.add(right).divide(new BigInteger("2"));
			int compare = (mid.multiply(mid)).compareTo(N);
			if(compare == 0) break;
			else if(compare == 1) right = mid.subtract(new BigInteger("1"));
			else left = mid.add(new BigInteger("1"));
		}
		System.out.println(mid.toString());
		sc.close();
	}
}