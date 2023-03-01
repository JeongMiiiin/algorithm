import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 백준 1764번 - 듣보잡
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] caseNum = br.readLine().split(" ");
		int N = Integer.parseInt(caseNum[0]);
		int M = Integer.parseInt(caseNum[1]);
		
		Map<String,Integer> noHearList = new HashMap<String,Integer>();
		for(int i=0; i < N; i++)
			noHearList.put(br.readLine(), 1);
		
		List<String> result = new ArrayList<String>();
		for(int i=0; i < M; i++) {
			String s = br.readLine();
			if(noHearList.get(s) != null) {
				result.add(s);
			} 
		}
		
		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		sb.append(result.size() + "\n");
		for(int i=0; i < result.size(); i++)
			sb.append(result.get(i) + "\n");
		
		System.out.println(sb.toString());
		
	}
}
