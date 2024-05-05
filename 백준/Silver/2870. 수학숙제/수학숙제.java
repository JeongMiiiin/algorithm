import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		List<BigInteger> list = new ArrayList<>();
		
		for(int i=0; i < N; i++) {
			StringBuilder sb = new StringBuilder();
			for(char c : sc.nextLine().toCharArray()) {
				if(c - '0' >= 0 && c - '0' <= 9) {
					sb.append(c);
				} else if(sb.length() > 0){
					list.add(new BigInteger(sb.toString()));
					sb.setLength(0);
				}
			}
			if(sb.length() > 0) list.add(new BigInteger(sb.toString()));
		}
		
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(BigInteger n : list) sb.append(n + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
}