import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//최소, 최대
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i=0; i < T; i++) {
			int n = Integer.parseInt(st.nextToken());
			min = Math.min(n, min);
			max = Math.max(n, max);
		}
		System.out.printf("%d %d",min,max);
	}
}
