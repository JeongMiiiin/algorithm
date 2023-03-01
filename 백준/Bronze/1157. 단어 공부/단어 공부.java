import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String st = br.readLine();
		st = st.toUpperCase();
		int[] freList = new int[26];
		
		int max = 0;
		for(int i=0; i < st.length(); i++) {
			int target = (int) st.charAt(i) - 65;
			freList[target]++;
			if(max < freList[target]) max = freList[target];
		}
		
		int cnt = 0;
		int idx = 0;
		for(int i=0; i < freList.length; i++) {
			if(max == freList[i]) {
				idx = i;
				cnt++;
				if(cnt > 1) break;
			}
		}
		
		if(cnt > 1) {
			System.out.println("?");
		} else {
			idx += 65;
			char ch = (char) idx;
			System.out.println(ch);
		}
		
		
	}
 
}