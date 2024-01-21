import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = 1;
		StringBuilder sb = new StringBuilder();
		while(sc.hasNext()) {
			int N = Integer.parseInt(sc.nextLine());
			if(N == 0) break; //종료시
			String[] arr = new String[N];
			for(int i=0; i < N; i++) arr[i] = sc.nextLine();
			int[] cnt = new int[N];
			for(int i=0; i < N * 2 - 1; i++) {
				StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
				int target = Integer.parseInt(st.nextToken());
				cnt[target - 1]++;
			}
			for(int i=0; i < N; i++) {
				if(cnt[i] == 1) {
					sb.append(t++ + " " + arr[i] + "\n");
					break;
				} 
			}
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}