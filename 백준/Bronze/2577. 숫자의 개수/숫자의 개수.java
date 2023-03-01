import java.util.Scanner;

//2577번 숫자의 개수
public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		int[] fre = new int[10];
		
		char[] total = Integer.toString(A*B*C).toCharArray();
		
		for(char c : total)
			fre[c - '0']++;
		
		StringBuilder sb = new StringBuilder();
		for(int f : fre)
			sb.append(f + "\n");
		
		System.out.print(sb);
		
	}
}