import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[][] arr = new int[N + 1][2];
		arr[0][0] = 1;
		for(int i=0; i < N; i++) {
			arr[i + 1][0] = arr[i][1];
			arr[i + 1][1] = arr[i][0] + arr[i][1];
		}
		
		System.out.println(arr[N][0] + " " + arr[N][1]);
		sc.close();
	}
}