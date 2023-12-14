import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		Map<Integer, Boolean> aList = new HashMap<>();
		Map<Integer, Boolean> bList = new HashMap<>();
		for(int i=0; i < A; i++) aList.put(sc.nextInt(), true);
		for(int i=0; i < B; i++) bList.put(sc.nextInt(), true);
		int result = 0;
		for(Integer keys : aList.keySet()) if(bList.get(keys) == null) result++;
		for(Integer keys : bList.keySet()) if(aList.get(keys) == null) result++;
		System.out.println(result);
		sc.close();
	}
}