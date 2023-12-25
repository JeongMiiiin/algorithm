import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(char c : br.readLine().toCharArray()) {
			if(c == 'X') { //X를 만났을 때
				if(++cnt == 4) { //4개가 들어갈 수 있을 때
					sb.append("AAAA");
					cnt = 0;
				}
			} else { //.을 만났을 때
				if(cnt == 2) {
					sb.append("BB");
					cnt = 0;
				}
				
				if(cnt == 0) sb.append(".");
				else { //만들 수 없을 때
					sb.setLength(0);
					sb.append(-1);
					break;
				}
			}
		}
		
		if(cnt == 2) sb.append("BB");
		else if(cnt != 0) {
			sb.setLength(0);
			sb.append(-1);
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}