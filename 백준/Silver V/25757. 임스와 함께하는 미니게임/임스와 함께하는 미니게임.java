import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int share = 0;
		switch(st.nextToken()) {
			case "Y" :
				share = 1;
				break;
			case "F" :
				share = 2;
				break;
			case "O" :
				share = 3;
				break;
		}
		
		Set<String> set = new HashSet<>();
		for(int i=0; i < N; i++) set.add(sc.nextLine());
		
		System.out.println(set.size() / share);
		sc.close();
	}
}