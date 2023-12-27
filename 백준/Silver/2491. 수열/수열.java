import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		int result = 1;
		
		int up = arr[0], down = arr[0], upCnt = 1, downCnt = 1;
		for(int i=1; i < N; i++) {
			if(up <= arr[i]) {
				if(++upCnt > result) result = upCnt;
			} else upCnt = 1;
			up = arr[i];
			if(down >= arr[i]) {
				if(++downCnt > result) result = downCnt;
			} else downCnt = 1;
			down = arr[i];
		}
		System.out.println(result);
		sc.close();
	}
}