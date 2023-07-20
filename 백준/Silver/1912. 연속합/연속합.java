import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] nList = new int[N];
		int[] dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < N; i++) nList[i] = Integer.parseInt(st.nextToken());
		dp[0] = nList[0];
		for(int i=1; i < N; i++) dp[i] = Math.max(dp[i - 1] + nList[i], nList[i]);
		
		int answer = Integer.MIN_VALUE;
		
		for(int i=0; i < N; i++) answer = Math.max(answer, dp[i]);
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}
}