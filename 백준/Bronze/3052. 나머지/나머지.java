import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//나머지
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<Integer> intSet = new HashSet<Integer>();
		int N = 42;
		for(int i=0; i < 10; i++) {
			int target = sc.nextInt() % N;
			intSet.add(target);
		}
		System.out.println(intSet.size());
		
	}
}
