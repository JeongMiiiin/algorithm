import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] arr = new int[W];
		st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < W; i++) arr[i] = Integer.parseInt(st.nextToken());
		int startHei = 0;
		int[] left = new int[W];
		for(int i=0; i < W; i++) {
			if(startHei <= arr[i]) startHei = arr[i];
			left[i] = startHei;
		}
		
		startHei = 0;
		int[] right = new int[W];
		for(int i=W - 1; i >= 0; i--) {
			if(startHei <= arr[i]) startHei = arr[i];
			right[i] = startHei;
		}
		
		int result = 0;
		for(int i=1; i < W - 1; i++) result += (Math.min(left[i], right[i]) - arr[i] >= 0 ? Math.min(left[i], right[i]) - arr[i] : 0);
		
		System.out.println(result);
		sc.close();
	}
}