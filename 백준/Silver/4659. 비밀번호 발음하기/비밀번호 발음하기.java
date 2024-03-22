import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(true) {
			boolean result = true;
			String s = sc.nextLine();
			if(s.equals("end")) break;
			else {
				int cnt = 1;
				char c = s.charAt(0);
				boolean flag = checkVowel(c);
				boolean include = flag;
				for(int i=1; i < s.length(); i++) {
					char cur = s.charAt(i);
					if(checkVowel(cur)) include = true; //모음인 경우 true
					
					if(checkVowel(cur) == flag) {
						if(++cnt > 2) { //모음이나 자음이 3개 연속으로 올 경우
							result = false;
							break;
						}
					} else { //자음 모음이 바뀐 경우 초기화
						flag = checkVowel(cur);
						cnt = 1;
					}
					//같은 글자가 연속적으로 두번올 때
					if(cur != 'e' && cur != 'o' && cur == s.charAt(i - 1)) {
						result = false;
						break;
					}
				}
				
				//모음이 한번이라도 포함이 안된 경우
				if(!include) result = false;
			}
			sb.append("<" + s + ">" + " is " + (result ? "" : "not ") + "acceptable.\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static boolean checkVowel(char c) {
		return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
	}
}