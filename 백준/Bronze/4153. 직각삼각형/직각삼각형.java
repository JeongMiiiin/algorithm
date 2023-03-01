import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//백준 4153번 직각삼각형
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			if((N + M + S) == 0) break;
			
			String ans = "wrong";
			if(N * N == (M * M + S * S )) {
				ans = "right";
			} else if(M * M == (N * N + S * S )) {
				ans = "right";
			} else if(S * S == (N * N + M * M )) {
				ans = "right";
			}
			
			bw.write(ans + "\n");
			
		}
		bw.close();
	}
}
