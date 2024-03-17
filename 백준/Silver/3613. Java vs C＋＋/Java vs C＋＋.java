import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int status = 0;
		int idx = 0;
		for(char c : s.toCharArray()) {
			if(c == '_') {
				if(idx == 0 || idx == s.length() - 1 || s.charAt(idx - 1) == '_' || status == 2) {
					status = 0;
					break;
				} else status = 1;
			} else if((int) c < 91) {
				if(idx == 0 || status == 1) {
					status = 0;
					break;
				} else status = 2;
			}
			idx++;
		}
		if(idx == s.length() && status == 0) status++;
		StringBuilder sb = new StringBuilder();
		if(status == 1) { //Java 형식이었을 떄
			boolean flag = false;
			for(char c : s.toCharArray()) {
				if(c != '_') {
					if(flag) {
						sb.append((char) (((int) c) - 32));
						flag = false;
					} else sb.append(c);
				} else flag = true;
			}
			
		} else if(status == 2) { //C++ 형식일 때
			for(char c : s.toCharArray()) {
				if(c < 91) {
					sb.append("_");
					sb.append((char) (((int) c) + 32));
				} else sb.append(c);
			}
		}else sb.append("Error!");
		
		System.out.println(sb.toString());
		sc.close();
	}
}