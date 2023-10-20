import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Map<Integer, Boolean> map = new HashMap<>();
		
		for(int i=0; i < N; i++) map.put(sc.nextInt(), true);
		
		int M = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++) {
			if(map.get(sc.nextInt()) != null) sb.append(1);
			else sb.append(0);
			
			sb.append(" ");
		}
		sb.setLength(sb.length() - 1);
		
		System.out.println(sb.toString());
		
		sc.close();
	}
}