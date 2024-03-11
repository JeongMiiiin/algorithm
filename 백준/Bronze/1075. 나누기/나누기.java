import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String N = sc.nextLine();
		int F = Integer.parseInt(sc.nextLine());
		String idx = "00";
		boolean flag = false;
		while(!flag) {
			int target = Integer.parseInt(N.substring(0, N.length() - 2) + idx);
			if(target % F != 0) idx = Integer.parseInt(idx) >= 9 ? Integer.toString(Integer.parseInt(idx) + 1) : "0" + Integer.toString(Integer.parseInt(idx) + 1);
			else flag = true;
				
		}
		System.out.println(idx);
		sc.close();
	}
}