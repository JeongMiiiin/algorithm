import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 1535 - 안녕
 * 
 * 세준이를 생각해준 사람은 총 N명
 * 체력은 100
 * 인사하는 사람에 따라 L[i]의 체력을 잃고, J[i]의 기쁨을 얻음
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		
		int health = 100;
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] W = new int[N + 1];
		for(int i=1; i <= N; i++) W[i] = Integer.parseInt(st.nextToken());
			
		st = new StringTokenizer(br.readLine(), " ");
		int[] V = new int[N + 1];
		for(int i=1; i <= N; i++) V[i] = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][health];
		
		for(int i=1; i <= N; i++) {
			for(int j=1; j < health; j++) {
				if(W[i] > j) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
			}
		}
		
		bw.write(dp[N][health - 1] + "\n");
		bw.close();
	}
}