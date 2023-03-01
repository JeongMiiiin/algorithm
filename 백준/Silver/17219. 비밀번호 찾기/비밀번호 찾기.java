import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//사이트리스트 만들기
		Map<String,String> siteList = new HashMap<String,String>();
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			siteList.put(st.nextToken(), st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++)
			sb.append(siteList.get(br.readLine()) + "\n");
		
		System.out.println(sb.toString());
		
	}
}
