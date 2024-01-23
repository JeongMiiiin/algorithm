import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = -1, resultR = 0, resultC = 0;
		for(int r=1; r <= 9; r++) {
			for(int c=1; c <= 9; c++) {
				int target = sc.nextInt();
				if(result < target) {
					result = target;
					resultR = r;
					resultC = c;
				}
			}
		}
		System.out.println(result);
		System.out.println(resultR + " " + resultC);
		sc.close();
	}
}