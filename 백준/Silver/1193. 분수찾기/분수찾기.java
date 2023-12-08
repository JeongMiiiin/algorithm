import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		int cnt = 1, cntSum = 0;
		while(true) {
			if(X <= cnt + cntSum) {
				if(cnt % 2 == 1) {
					System.out.println((cnt - (X - cntSum - 1)) + "/" + (X - cntSum));
				} else {
					System.out.println((X - cntSum) + "/" + (cnt - (X - cntSum - 1)));
				}
				break;
			} else {
				cntSum += cnt;
				cnt++;
			}
		}
		
		sc.close();
	}
}