import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		List<Integer> arr = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int X = sc.nextInt();
		
		for(int i=0; i < T; i++) {
			int t = sc.nextInt();
			if(X > t) arr.add(t);
		}
		
		for(int i=0; i < arr.size(); i++)
			System.out.print(arr.get(i) + " ");
	}
}