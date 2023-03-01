import java.util.Arrays;
import java.util.Scanner;

// 백준 2751 수 정렬하기 2
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] numbers = new int[N];
		for(int i=0; i < N; i++)
			numbers[i] = sc.nextInt();
		Arrays.sort(numbers);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i < N; i++)
			sb.append(numbers[i] + "\n");
		
		System.out.println(sb.toString());
		
	}
}
