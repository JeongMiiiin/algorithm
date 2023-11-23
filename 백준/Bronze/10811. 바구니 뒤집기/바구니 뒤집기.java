import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		List<Integer> arr = new ArrayList<>();
		for(int i=1; i <= N; i++) arr.add(i);
		for(int i=0; i < M; i++) {
			int start = sc.nextInt() - 1;
			int end = sc.nextInt() - 1;
			while(start < end) {
				arr.add(start++, arr.get(end));
				arr.remove(end + 1);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < arr.size(); i++) sb.append(arr.get(i) + " ");
		
		System.out.println(sb.toString());
		sc.close();
	}
}