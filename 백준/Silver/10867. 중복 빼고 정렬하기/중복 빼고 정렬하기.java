import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<Integer> list = new ArrayList<>();
		for(int i=0; i < N; i++) list.add(sc.nextInt());
		Set<Integer> set = new HashSet<>(list);
		list = new ArrayList<>(set);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < list.size(); i++) sb.append(list.get(i) + " ");
 		System.out.println(sb.toString());
		sc.close();
	}
}