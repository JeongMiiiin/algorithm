import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double N = sc.nextDouble();
		double L = sc.nextDouble();
		double W = sc.nextDouble();
		double H = sc.nextDouble();
		double left = 0, right = Math.min(Math.min(L, W), H);
		while(left < right) {
			double mid = (left + right) / 2;
			double rowCnt = Math.floor(L / mid);
			double colCnt = Math.floor(W / mid);
			double heiCnt = Math.floor(H / mid);
			if(N > rowCnt * colCnt * heiCnt) {
				if(right == mid) break;
				right = mid;
			} else {
				if(left == mid) break;
				left = mid;
			}
		}
		System.out.println(left);
		sc.close();
	}
}