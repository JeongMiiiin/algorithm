import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static Set<Integer> set;
	static int[] arr;
	static int N, R;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = 0;
		set = new HashSet<>();
		arr = new int[N];
		for(int i=0; i < N; i++) {
			arr[i] = sc.nextInt();
			M += arr[i];
			set.add(M);
			set.add(arr[i]);
		}
		R = 2;
		while(R <= N - 1) {
			comb(0, 0, 0);
			R++;
		}
		
		int result = M - set.size();
		
		System.out.println(result);
		sc.close();
	}
	
	private static void comb(int cnt, int start, int total) {
		if(cnt == R) {
			set.add(total);
			return;
		}
		
		for(int i=start; i < N; i++) comb(cnt + 1, i + 1, total + arr[i]);
	}
	
}