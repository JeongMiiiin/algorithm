import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] nArr = new long[N];
		st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < N; i++) nArr[i] = Integer.parseInt(st.nextToken());
		long[] mArr = new long[M];
		st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < M; i++) mArr[i] = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int idx = 0, nIdx = 0, mIdx = 0;
		
		while(idx++ < N + M) {
			if(nIdx < N && mIdx < M) {
				if(nArr[nIdx] <= mArr[mIdx]) sb.append(nArr[nIdx++] + " ");
				else sb.append(mArr[mIdx++] + " ");
			} else if(nIdx < N) sb.append(nArr[nIdx++] + " ");
			else sb.append(mArr[mIdx++] + " ");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}