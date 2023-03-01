import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * 백준 1620번 나는야 포켓몬 마스터 이다솜
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] caseNum = br.readLine().split(" ");
		int N = Integer.parseInt(caseNum[0]);
		int M = Integer.parseInt(caseNum[1]);
		
		Map<String,Integer> pocketMonString = new HashMap<String,Integer>();
		Map<Integer,String> pocketMonInt = new HashMap<Integer,String>();
		
		for(int i=1; i <= N; i++) {
			String s = br.readLine();
			pocketMonString.put(s, i);
			pocketMonInt.put(i, s);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++) {
			String s = br.readLine();
			if(isInteger(s)) {
				int n = Integer.parseInt(s);
				sb.append(pocketMonInt.get(n) + "\n");
			} else sb.append(pocketMonString.get(s) + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static boolean isInteger(String strValue) {
	    try {
	      Integer.parseInt(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	  }
}
