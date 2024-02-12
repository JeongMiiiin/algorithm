import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			String fox = sc.nextLine();
			
			Set<String> set = new HashSet<>();
			while(true) {
				String s = sc.nextLine();
				if(s.equals("what does the fox say?")) break;
				String[] info = s.split(" ");
				set.add(info[2]);
			}
			StringTokenizer st = new StringTokenizer(fox, " ");
			while(st.hasMoreTokens()) {
				String voice = st.nextToken();
				if(!set.contains(voice)) sb.append(voice + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}