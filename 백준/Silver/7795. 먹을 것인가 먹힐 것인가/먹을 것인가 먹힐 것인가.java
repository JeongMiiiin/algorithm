import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
			int[] Aarr = new int[A], Barr = new int[B];
			st = new StringTokenizer(sc.nextLine(), " ");
			for(int i=0; i < A; i++) Aarr[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(sc.nextLine(), " ");
			for(int i=0; i < B; i++) Barr[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(Aarr);
			Arrays.sort(Barr);
			int result = 0;
			for(int i=0; i < A; i++) {
				int left = 0, right = B - 1;
				while(left < right) {
					int mid = (left + right) / 2;
					if(Barr[mid] >= Aarr[i]) right = mid;
					else left = mid + 1;
				}
				result += left;
				if(Barr[left] < Aarr[i]) result++;
			}
			sb.append(result + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}