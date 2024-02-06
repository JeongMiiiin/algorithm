import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		boolean[] peoples = new boolean[N];
		boolean[] hamburgers = new boolean[N];
		int j = 0;
		for(char c : sc.next().toCharArray()) {
			if(c == 'P') peoples[j++] = true;
			else hamburgers[j++] = true;
		}
		int result = 0, cur = 0;
		for(int i=0; i < N; i++) {
			if(!peoples[i]) continue; //자리에 사람이 없을 때
			
			cur = i - K >= 0 ? i - K : 0;
			while(cur < N && cur <= i + K) {
				if(hamburgers[cur]) {
					result++;
					hamburgers[cur] = false;
					break;
				} else cur++;
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}