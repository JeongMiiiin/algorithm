import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int result = 0;
		Set<String> set = new HashSet<>();
		for(int i=0; i < N; i++) {
			String s = sc.nextLine();
			if(s.equals("ENTER")) { //새로운 사람이 입장했을 때
				result += set.size(); //이제까지 입력한 인사 채팅 횟수 더하기
				set.clear(); //인사 채팅 초기화
			} else if(!set.contains(s)) set.add(s); //인사를 입력하지 않은 사람인 경우 set에 추가
		}
		result += set.size(); //이제까지 입력한 인사 채팅 횟수 더하기
		System.out.println(result);
		sc.close();
	}
}