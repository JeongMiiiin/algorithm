import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Set<String> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < N; i++) {
			String s = st.nextToken();
			if(s.length() > 5 && s.substring(s.length() - 6, s.length()).equals("Cheese")) set.add(s);
		}
		
		System.out.println(set.size() > 3 ? "yummy" : "sad");
		sc.close();
	}
}