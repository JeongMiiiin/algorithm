import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//백준 7568번 덩치
//자신보다 키와 몸무게가 큰 경우에 값 처리
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		
		int[] rank = new int[T];
		
		int[][] body = new int[T][2];
		
		for(int i=0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			body[i][0] = Integer.parseInt(st.nextToken());
			body[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i < T; i++) {
			rank[i]++;
			for(int j=0; j < T; j++) {
				if(i == j) continue;
				
				if (body[i][0] < body[j][0] && body[i][1] < body[j][1])
					rank[i]++;
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < T; i++)
			sb.append(rank[i] + " ");
		bw.write(sb.toString());
		bw.close();
		
	}
}
