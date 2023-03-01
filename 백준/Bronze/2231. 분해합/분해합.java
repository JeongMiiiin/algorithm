import java.util.Scanner;

// 백준 2231번 분해합

/*
 * 어떤 자연수 N
 * 
 * 198(생성자) + 1 + 9 + 8 => 216 (분해합)
*/
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		
		int ans = 0;
		for(int i= N / 2; i > 0; i--) {
			int M = N - i;
			char[] MList = Integer.toString(M).toCharArray();
			int sum = 0;
			for(char c : MList)
				sum += c - '0';
			if(sum == i) {
				ans = M;
				break;
			} 
		}
		
		System.out.println(ans);
	}
}
