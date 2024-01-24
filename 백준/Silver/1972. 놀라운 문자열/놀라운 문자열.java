import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(true) {
			String s = sc.nextLine();
			if(s.equals("*")) break;
			boolean flag = true;
			int idx = 0;
			outer : while(idx < s.length() - 2) {
				Set<String> set = new HashSet<>();
				for(int start = 0; start < s.length() - idx - 1; start++) {
					StringBuilder temp = new StringBuilder();
					temp.append(s.charAt(start));
					temp.append(s.charAt(start + idx + 1));
					if(set.contains(temp.toString())) {
						flag = false;
						break outer;
					} else set.add(temp.toString());
				}
				idx++;
			}
			if(flag) sb.append(s + " is surprising.\n");
			else sb.append(s + " is NOT surprising.\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}