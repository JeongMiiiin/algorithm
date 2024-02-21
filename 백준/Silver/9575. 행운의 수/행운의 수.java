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
			int A = Integer.parseInt(sc.nextLine());
			int[] AInfo = new int[A];
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			for(int i=0; i < A; i++) AInfo[i] = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(sc.nextLine());
			int[] BInfo = new int[B];
			st = new StringTokenizer(sc.nextLine(), " ");
			for(int i=0; i < B; i++) BInfo[i] = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(sc.nextLine());
			int[] CInfo = new int[C];
			st = new StringTokenizer(sc.nextLine(), " ");
			for(int i=0; i < C; i++) CInfo[i] = Integer.parseInt(st.nextToken());
			
			Set<Integer> result = new HashSet<>();
			for(int i=0; i < A; i++) for(int j=0; j < B; j++) for(int z=0; z < C; z++) if(checkLuck(AInfo[i] + BInfo[j] + CInfo[z])) result.add(AInfo[i] + BInfo[j] + CInfo[z]);
			sb.append(result.size() + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static boolean checkLuck(int target) {
		boolean check = true;
		while(target > 0) {
			int remain = target % 10;
			if(remain != 5 && remain != 8) return false;
			target /= 10;
		}
		
		return check;
	}
	
}