import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Character>[] map = new ArrayList[5];
		for(int i=0; i < 5; i++) {
			map[i] = new ArrayList<>();
			for(char c : sc.nextLine().toCharArray()) map[i].add(c);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < 15; i++) {
			for(int j=0; j < 5; j++) {
				if(i < map[j].size()) sb.append(map[j].get(i));
			}
		}
		System.out.println(sb.toString());
		sc.close();
	}
}