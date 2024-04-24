import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static class Problem implements Comparable<Problem> {
		int idx, score;
		public Problem(int idx, int score) {
			this.idx = idx;
			this.score = score;
		}
		
		@Override
		public int compareTo(Problem o) {
			// TODO Auto-generated method stub
			return o.score - this.score;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Problem[] arr = new Problem[8];
		for(int i=0; i < 8; i++) arr[i] = new Problem(i + 1, Integer.parseInt(sc.nextLine()));
		Arrays.sort(arr);
		int total = 0;
		int[] rank = new int[5];
		for(int i=0; i < 5; i++) {
			total += arr[i].score;
			rank[i] = arr[i].idx;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(total + "\n");
		Arrays.sort(rank);
		for(int i=0; i < 5; i++) sb.append(rank[i] + " ");
		System.out.println(sb.toString());
		sc.close();
	}
}