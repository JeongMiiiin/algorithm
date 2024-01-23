import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = 0, idx = 0, remainIdx = 0;
		String remain = "";
		String s = sc.nextLine();
		while(idx < s.length()) {
			char target = s.charAt(idx++);
			boolean flag = false;
			while(!flag) {
				if(remainIdx >= remain.length()) {
					remainIdx = 0;
					remain = Integer.toString(++result);
				}
				while(remainIdx < remain.length()) {
					if(remain.charAt(remainIdx++) == target) {
						flag = true;
						break;
					}
				}
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}