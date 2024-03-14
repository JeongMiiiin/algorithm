import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		int[] firstReward = {0, 5000000, 3000000, 3000000, 2000000, 2000000, 2000000, 500000, 500000, 500000, 500000, 300000, 300000, 300000, 300000, 300000, 100000, 100000, 100000, 100000, 100000, 100000};
		int[] secondReward = {0, 5120000, 2560000, 2560000, 1280000, 1280000, 1280000, 1280000, 640000, 640000, 640000, 640000, 640000, 640000, 640000, 640000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000, 320000};
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int result = 0;
			if(first < firstReward.length) result += firstReward[first];
			if(second < secondReward.length) result += secondReward[second];
			sb.append(result + "\n");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}