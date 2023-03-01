import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[] scoreList = new int[N];
			int sum = 0;
			for(int i=0; i < N; i++) {
				scoreList[i] = Integer.parseInt(st.nextToken());
				sum += scoreList[i];
			}
			double avg = sum / N;
			double cnt = 0;
			for(int i=0; i < N; i++) if(scoreList[i] > avg) cnt++;
			double ans = cnt / N * 100.000;
			System.out.println(String.format("%.3f",ans) + "%");
		}
	}
}
