import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int K = sc.nextInt();
		int idx = 0;
		while(K > Math.pow(2, idx)) idx++;
		int start = (int) Math.pow(2, idx);
		sb.append(start + " ");
		int result = 0;
		while(K > 0) {
			if(K >= start) {
				K -= start;
			} else {
				start /= 2;
				result++;
			}
		}
		
		sb.append(result);
		System.out.println(sb.toString());
		sc.close();
	}
}