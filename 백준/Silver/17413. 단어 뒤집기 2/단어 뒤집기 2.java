import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		boolean flag = false;
		for(char c : s.toCharArray()) {
			if(c == ' ') {
				sb.append(temp);
				temp.setLength(0);
				sb.append(c);
			} else {
				if(c == '<') {
					sb.append(temp);
					temp.setLength(0);
					flag = true; //똑바로 받기 시작
				}
				
				if(flag) sb.append(c);
				else temp.insert(0, c);
				
				if(c == '>') flag = false;
			}
		}
		if(temp.length() > 0) sb.append(temp);
		System.out.println(sb.toString());
		br.close();
	}
}