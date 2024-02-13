import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();
		Map<String, String> group = new HashMap<>();
		Map<String, String> member = new HashMap<>();
		for(int i=0; i < N; i++) {
			String groupName = sc.nextLine();
			int num = Integer.parseInt(sc.nextLine());
			String[] members = new String[num];
			for(int j=0; j < num; j++) {
				String name = sc.nextLine();
				members[j] = name;
				member.put(name, groupName);
			}
			Arrays.sort(members);
			StringBuilder sb = new StringBuilder();
			for(String s : members) sb.append(s + "\n");
			group.put(groupName, sb.toString());
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < M; i++) {
			String problem = sc.nextLine();
			int problemNum = Integer.parseInt(sc.nextLine());
			if(problemNum == 0) sb.append(group.get(problem)); //팀의 이름이 주어질 경우
			else sb.append(member.get(problem) + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}