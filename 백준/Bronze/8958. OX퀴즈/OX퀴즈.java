import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			sb = new StringBuilder(br.readLine());
			int cnt = 0;
			int sum = 0;
			for(int i=0; i < sb.length(); i++) {
				String targetText = sb.substring(i, i + 1);
				if(targetText.equals("O")) {
					cnt++;
					sum += cnt;
				} else {
					cnt = 0;
				}
			}
			sb.setLength(0);
			bw.write(sum + "\n");
		}
		bw.close();
	}
}
