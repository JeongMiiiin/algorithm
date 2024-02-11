import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Set<String> set = new HashSet<>();
		for(int i=0; i < N * 2 - 1; i++) {
			String maratoner = sc.nextLine();
			if(set.contains(maratoner)) set.remove(maratoner);
			else set.add(maratoner);
		}
		
		for(String remain : set) System.out.println(remain);
		sc.close();
	}
}