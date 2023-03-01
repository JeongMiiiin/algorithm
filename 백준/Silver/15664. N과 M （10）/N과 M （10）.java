import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
	static int N, M;
	static LinkedHashSet<String> ans = new LinkedHashSet<>();
	static int[] inputs, numbers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		inputs = new int[N];
		numbers = new int[M];
		for(int i=0; i < N; i++) inputs[i] = sc.nextInt();
		Arrays.sort(inputs);
		
		comb(0, 0);
		ans.forEach(System.out::println);
		sc.close();
	}
	private static void comb(int cnt, int start) {
		if(cnt == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < M;i++) sb.append(numbers[i] + " ");
			ans.add(sb.toString());
			return;
		}
		
		for(int i=start; i < N; i++) {
			
			numbers[cnt] = inputs[i];
			comb(cnt + 1, i + 1);
		}
	}
}
