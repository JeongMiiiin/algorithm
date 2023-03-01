import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			String target = st.nextToken();
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < target.length(); i++) {
				String targetText = target.substring(i, i + 1);
				for(int j=0; j < R; j++)
					sb.append(targetText);
			}
			bw.write(sb.toString() + "\n");
		}
		bw.close();
	}
}
