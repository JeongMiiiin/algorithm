import java.util.Scanner;

public class Main {
	/*
	 * BOJ 3040 - 백설공주와 일곱 난쟁이를 찾아라
	 * 9개의 케이스가 주어지고
	 * 7개가 더해져 100이 되는 값을 찾아야 한다
	*/
	static int N = 9;
	static int R = 7;
	static int[] inputs = new int[N];
	static int[] numbers = new int[R];
	static boolean stop = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i < N; i++)
			inputs[i] = sc.nextInt();
		
		comb(0,0);
	}

	private static void comb(int cnt, int start) {
		if(stop) return;
		if(cnt == R) {
			int sum = 0;
			for(int n : numbers)
				sum += n;
			if(sum == 100) {
                stop = true;
				StringBuilder sb = new StringBuilder();
				for(int n : numbers)
					sb.append(n + "\n");
				System.out.print(sb);
			}
			return;
		}
		
		for(int i = start; i < N; i++) {
			numbers[cnt] = inputs[i];
			comb(cnt + 1, i + 1);
			if(stop) break;
		}
		
	}
	
	
}
