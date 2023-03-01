import java.util.Scanner;

/*
 * 백준 1074번 - Z
*/
public class Main {
	static int cnt = 0, r, c;
	static boolean findStatus;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = (int) Math.pow(2, sc.nextInt());
		r = sc.nextInt();
		c = sc.nextInt();
		
		division(0, 0, N);
		
		System.out.println(cnt);
		sc.close();
	}
	
	private static void division(int startR, int startC, int size) {
		if(findStatus) return;
		if(startR + size < r || startC + size < c) {
			cnt += size * size;
			return;
		}
		
		if(size > 2) {
			int half = size / 2;
			division(startR, startC, half); //1분면
			division(startR, startC + half, half); //2분면
			division(startR + half, startC, half); //3분면
			division(startR + half, startC + half, half); //4분면
			return;
		}
		
		outer : for(int i=startR; i < startR + size; i++) {
			for(int j=startC; j < startC + size; j++) {
				if(i == r && j == c) {
					findStatus = true;
					break outer;
				}
				cnt++;
			}
		}
	}
}
