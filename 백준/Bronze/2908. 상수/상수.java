import java.util.Scanner;

//백준 2908번 상수
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] A = Integer.toString(sc.nextInt()).toCharArray();
		char[] B = Integer.toString(sc.nextInt()).toCharArray();
		
		
		int rA = 0, rB = 0;
		for(int i=2; i > -1; i--) {
			rA += (A[i] - '0') * Math.pow(10, i);
			rB += (B[i] - '0') * Math.pow(10, i);
		}
		
		int max = Math.max(rA, rB);
		
		System.out.println(max);
		
	}
}