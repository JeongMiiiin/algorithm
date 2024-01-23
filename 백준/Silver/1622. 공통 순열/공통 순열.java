import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String a = br.readLine();
			if(a == null) break;
			String b = br.readLine();
			int[] aList = new int[26];
			int[] bList = new int[26];
			for(char c : a.toCharArray()) aList[c - 'a']++;
			for(char c : b.toCharArray()) bList[c - 'a']++;
			
			StringBuilder temp = new StringBuilder();
			for(int i=0; i < 26; i++) {
				if(aList[i] > 0 && bList[i] > 0) {
					int cnt = Math.min(aList[i], bList[i]);
					for(int j=0; j < cnt; j++) temp.append((char) (i + 'a'));
				}
			}
			sb.append(temp.toString() + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}