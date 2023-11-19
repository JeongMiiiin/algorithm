import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] nList = new int[N + 1];
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			while(start <= end) nList[start++] = target;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=1; i <= N; i++) sb.append(nList[i] + " ");
		System.out.println(sb.toString());
		br.close();
	}
}