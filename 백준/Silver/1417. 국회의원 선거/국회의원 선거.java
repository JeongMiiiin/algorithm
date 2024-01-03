import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N - 1];
		int temp = sc.nextInt();
		for(int i=0; i < N - 1; i++) arr[i] = sc.nextInt();
		
		int result = 0;
		boolean flag = N == 1;
		while(!flag) {
			Arrays.sort(arr);
			flag = true;
			if(temp <= arr[arr.length - 1]) {
				temp++;
				arr[arr.length - 1]--;
				result++;
				flag = false;
			}
		}
		
		System.out.println(result);
		sc.close();
	}
}