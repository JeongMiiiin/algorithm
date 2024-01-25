import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();
		List<String> list = new ArrayList<>();
		int textLen = 0;
		for(int i=0; i < N; i++) {
			String s = sc.nextLine();
			textLen += s.length();
			list.add(s);
		}
		int share = (M - textLen) / (N - 1);
		int remain = (M - textLen) % (N - 1);
		for(int i=1; i < list.size(); i++) {
			String s = list.get(i);
			if(s.charAt(0) - 'a' >= 0 && remain > 0) {
				remain--;
				s = "_" + s;
				list.set(i, s);
			}
		}
		if(remain > 0) { //뿌렸는데도 남았을 때
			for(int i= list.size() - 1; i > 0; i--) {
				String s = list.get(i);
				if(s.charAt(0) == '_') continue;
				else if(remain > 0) {
					remain--;
					s = "_" + s;
					list.set(i, s);
					if(remain == 0) break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < list.size(); i++) {
			sb.append(list.get(i));
			if(i < list.size() - 1) for(int j=0; j < share; j++) sb.append("_");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}
}