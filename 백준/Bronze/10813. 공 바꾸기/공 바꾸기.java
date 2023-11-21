import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int[] nList = new int[N + 1];
		for(int i=1; i <= N; i++) nList[i] = i;
		int M = Integer.parseInt(st.nextToken());
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int temp = nList[start];
			nList[start] = nList[end];
			nList[end] = temp;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <= N; i++) sb.append(nList[i] + " ");
		
		System.out.println(sb.toString());
		
		br.close();
	}
}