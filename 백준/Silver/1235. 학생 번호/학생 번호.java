import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		String[] arr = new String[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextLine();
		int k = 0;
		int len = arr[0].length();
		while(true) {
			k++;
			boolean temp = true;
			Set<String> set = new HashSet<>();
			for(int i=0; i < N; i++) {
				String s = arr[i].substring(len - k, len);
				if(set.contains(s)) {
					temp = false;
					break;
				} else set.add(s);
			}
			
			if(temp) break; //통과했을 때
		}
		
		System.out.println(k);
		sc.close();
	}
}