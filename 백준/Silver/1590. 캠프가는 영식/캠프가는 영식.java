import java.util.Scanner;

/*
 * 백준 1590 - 캠프가는 영식
 * */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int T = sc.nextInt();
		int result = Integer.MAX_VALUE;
		int start, interval, cnt, idx;
		for(int i=0; i < N; i++) {
			start = sc.nextInt();
			interval = sc.nextInt();
			cnt = sc.nextInt();
			idx = 0;
			while(idx < cnt) {
				if(start - T >= 0 && result >= start - T) {
					result = start - T;
					break;
				} else {
					start += interval;
					idx++;
				}
			}
		}
		
		if(result == Integer.MAX_VALUE) result = -1;
		System.out.println(result);
		sc.close();
	}
}