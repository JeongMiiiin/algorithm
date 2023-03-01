import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] payInfo = {500,100,50,10,5,1};
		int change = 1000 - N;
		int idx = 0, ans = 0;
		while(change > 0) {
			if(change / payInfo[idx] > 0) {
				int share = change / payInfo[idx];
				ans += share;
				change -= payInfo[idx] * share;
			}
			idx++;
		}
		System.out.println(ans);
	}
}
