import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int m = sc.nextInt();
		int total = 0;
		for(int i = m - 1; i >=0; i--) {
			int target = sc.nextInt();
			total += Math.pow(A, i) * target;
		}
		
		StringBuilder sb = new StringBuilder();
		int startIdx = 0;
		while(total - ( Math.pow(B, startIdx) * B ) >= 0) startIdx++;
		while(startIdx >= 0) {
			int share = (int) (total / Math.pow(B, startIdx));
			
			sb.append(share + " ");
			total -= share * Math.pow(B, startIdx);
			startIdx--;
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}