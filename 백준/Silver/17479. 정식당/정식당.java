import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine()," ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		Map<String, Long> normal = new HashMap<>();
		Map<String, Long> special = new HashMap<>();
		Set<String> service = new HashSet<>();
		for(int i=0; i < A; i++) {
			st = new StringTokenizer(sc.nextLine()," ");
			normal.put(st.nextToken(), Long.parseLong(st.nextToken()));
		}
		for(int i=0; i < B; i++) {
			st = new StringTokenizer(sc.nextLine()," ");
			special.put(st.nextToken(), Long.parseLong(st.nextToken()));
		}
		for(int i=0; i < C; i++) service.add(sc.nextLine());
		boolean result = true;
		long normalPrice = 0, specialPrice = 0, serviceCnt = 0;
		int N = Integer.parseInt(sc.nextLine());
		for(int i=0; i < N; i++) {
			String menu = sc.nextLine();
			if(normal.get(menu) != null) normalPrice += normal.get(menu); //일반메뉴를 시켰을 때
			else if(special.get(menu) != null) specialPrice += special.get(menu); //특별메뉴를 시켰을 때
			else serviceCnt++; //서비스메뉴를 시켰을 때
		}
		
		if(serviceCnt > 1 || (normalPrice < 20000 && specialPrice > 0) || (normalPrice + specialPrice < 50000 && serviceCnt > 0)) result = false; //잘못된 주문 정보인 경우
		
		System.out.println(result ? "Okay" : "No");
		sc.close();
	}
}