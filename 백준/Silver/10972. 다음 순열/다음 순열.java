import java.util.Scanner;

public class Main {
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static boolean nextPerm(int[] arr) {
		int i= arr.length - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if(i <= 0) return false;
		
		int j = arr.length - 1;
		while(arr[j] <= arr[i - 1]) j--;
		
		swap(arr, i - 1, j);
		
		j = arr.length - 1;
		while(i < j) swap(arr, i++ , j--);
		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		if(nextPerm(arr)) for(int i=0; i < N; i++) sb.append(arr[i] + " ");
		else sb.append(-1);
		
		System.out.println(sb.toString());
		sc.close();
	}
}