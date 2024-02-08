import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Set<String> set = new HashSet<>();
		set.add("ChongChong");
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			if(set.contains(s2)) set.add(s1);
			if(set.contains(s1)) set.add(s2);
		}
		
		System.out.println(set.size());
		sc.close();
	}
}