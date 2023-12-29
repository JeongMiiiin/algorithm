import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int i=1; i <= X; i++) {
			int classCnt = sc.nextInt();
			int[] arr = new int[classCnt];
			for(int j=0; j < classCnt; j++) arr[j] = sc.nextInt();
			Arrays.sort(arr);
			
			int first = arr[0];
			int last = arr[classCnt - 1];
			int gap = 0;
			for(int j= classCnt - 1; j > 0; j--) gap = Math.max(gap, arr[j] - arr[j - 1]);
			sb.append("Class " + i + "\n");
			sb.append("Max " + last + ", Min " + first + ", Largest gap " + gap + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}