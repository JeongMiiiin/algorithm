import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = Integer.parseInt(sc.nextLine());
		int N = Integer.parseInt(sc.nextLine());
		List<Integer> list = new ArrayList<>();
		for(int i=M; i <= N; i++) if(check(i)) list.add(i);
		
		int total = 0;
		for(int n : list) total += n;
		
		System.out.println(total > 0 ? total + "\n" + list.get(0) : -1);
		sc.close();
	}
	
	private static boolean check(int target) {
		boolean result = (target != 1);
		for(int i=2; i <= Math.sqrt(target); i++) if(target % i == 0) result = false;
		return result;
	}
}