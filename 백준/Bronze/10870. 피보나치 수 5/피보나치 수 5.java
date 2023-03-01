import java.util.Scanner;

/*
 * 피보나치 수 5
*/
public class Main {
	static int N;
	static int[] NList;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		NList = new int[N+1]; 
		
		NList[0] = 0;
		if(N > 0) NList[1] = 1;
		
		if(N > 1) recur(N,2);
		
		System.out.println(NList[N]);
		sc.close();
	}

	private static void recur(int target, int start) {
		if(target == start) {
			NList[start] = NList[start - 1] + NList[start - 2];
			return;
		}
		NList[start] = NList[start - 1] + NList[start - 2];
		
		recur(target,start + 1);
		
	}
}
