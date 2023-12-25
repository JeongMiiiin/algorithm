import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Map<Long, Integer> map = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int P = sc.nextInt();
		int result = 0;
		long temp = A;
		map.put(temp, result++);
		while(map.get(calcSquare(temp, P)) == null) {
			temp = calcSquare(temp, P);
			map.put(temp, result++);
		}
		
		System.out.println(map.get(calcSquare(temp, P)));
		sc.close();
	}
	
	private static long calcSquare(long num, int squareCnt) {
		long result = 0;
		while(num > 0) {
			result += Math.pow(num % 10, squareCnt);
			num /= 10;
		}
		
		return result;
	}
}