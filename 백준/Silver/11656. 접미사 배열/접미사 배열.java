import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String target = sc.nextLine();
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(int i = target.length() - 1; i >= 0; i--) {
			sb.insert(0, target.charAt(i));
			list.add(sb.toString());
		}
		Collections.sort(list);
		StringBuilder result = new StringBuilder();
		for(String s : list) result.append(s + "\n");
		
		System.out.println(result.toString());
		sc.close();
	}
}