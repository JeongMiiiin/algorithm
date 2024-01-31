import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[] arr = new int[4];
		for(int i=0; i < N; i++) {
			String[] info = sc.nextLine().split("/");
			if(Integer.parseInt(info[1]) == 2) arr[2]++;
			else arr[Integer.parseInt(info[0])]++;
		}
		int result = arr[2] % 2 == 0 ? arr[2] / 2 : arr[2] / 2 + 1;
		if(arr[2] % 2 == 1) arr[1] -= 2;
		if(arr[3] > 0) {
			result += arr[3];
			arr[1] -= arr[3];
		}
		if(arr[1] > 0) result += arr[1] % 4 == 0 ? arr[1] / 4 : arr[1] / 4 + 1;
		
		System.out.println(result);
		sc.close();
	}
}