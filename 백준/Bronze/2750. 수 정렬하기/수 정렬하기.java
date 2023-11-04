import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] nList = new int[N];
		for(int i=0; i < N; i++) nList[i] = sc.nextInt();
		Arrays.sort(nList);
		for(int i=0; i < N; i++) System.out.println(nList[i]);
		sc.close();
	}
}