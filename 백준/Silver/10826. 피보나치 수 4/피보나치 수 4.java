import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		if(n > 1) {
			BigInteger[] arr = new BigInteger[n + 1];
			arr[0] = new BigInteger("0");
			arr[1] = new BigInteger("1");;
			int idx = 2;
			while(idx <= n) {
				arr[idx] = arr[idx - 1].add(arr[idx - 2]);
				idx++;
			}
			
			System.out.println(arr[n]);
		} else System.out.println(n);
		
		sc.close();
	}
}