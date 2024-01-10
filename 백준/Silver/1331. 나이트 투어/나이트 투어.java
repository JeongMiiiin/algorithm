import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<String> set = new HashSet<>();
		boolean flag = true;
		String s = sc.nextLine();
		char[] start = s.toCharArray();
		char[] temp = Arrays.copyOf(start, start.length);
		set.add(s);
		//좌상상, 좌좌상, 우상상, 우우상, 우하하, 우우하, 좌좌하, 좌하하
		int[] dr1 = {2, 1, 2, 1, -2, -1, -1, -2};
		int[] dc1 = {-1, -2, 1, 2, 1, 2, -2, -1};
		for(int i=1; i < 36; i++) {
			String t = sc.nextLine();
			char[] target = t.toCharArray();
			boolean tempFlag = false;
			for(int d=0; d < 8; d++) {
				int nr = (temp[1] - '0') + dr1[d];
				int nc = (temp[0] - '0') + dc1[d];
				if(nr == target[1] - '0' && nc == target[0] - '0') {
					tempFlag = true;
					temp = Arrays.copyOf(target, target.length);
					break;
				}
			}
			if(!tempFlag) {
				flag = tempFlag;
				break;
			}
			if(set.contains(t)) {
				flag = false;
				break;
			} else set.add(t);
		}
		
		if(flag) {
			boolean tempFlag = false;
			for(int d=0; d < 8; d++) {
				int nr = (temp[1] - '0') + dr1[d];
				int nc = (temp[0] - '0') + dc1[d];
				if(nr == start[1] - '0' && nc == start[0] - '0') {
					tempFlag = true;
					break;
				}
			}
			flag = tempFlag;
		}
		
		System.out.println(flag ? "Valid" : "Invalid");
		sc.close();
	}
}