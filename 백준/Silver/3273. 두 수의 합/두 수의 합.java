import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		Arrays.sort(arr);
		int x = sc.nextInt();
		int result = 0;
		int left = 0, right = N - 1;
		while(left < right) {
			int total = arr[left] + arr[right]; 
			//짝을 찾았을 때
			if(total == x) {
				result++;
				left++;
				right--;
			} else if(total < x) left++; //값이 작을 떄
			else right--; //값이 클 때
		}
		
		System.out.println(result);
		sc.close();
	}
}