import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 백준 9375 패션왕 신해빈
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int N, ans;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			Map<String, Integer> clothes = new HashMap<String, Integer>();
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				String category = st.nextToken();
				clothes.put(category, clothes.getOrDefault(category, 0) + 1);
			}
			
			int ans = 1;
			for(Map.Entry<String, Integer> pair : clothes.entrySet()) {
				ans *= (pair.getValue() + 1);
			}
			
			ans--;
			
			bw.write(ans + "\n");
		}
		
		bw.close();
	}
	
}