import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();
		String[] arr = new String[N];
		for(int i=0; i < N; i++) arr[i] = sc.nextLine();
		
		StringBuilder result = new StringBuilder();
		int distance = 0;
		int[] alphabet = new int[26];
		for(int i=0; i < M; i++) {
			Arrays.fill(alphabet, 0);
			for(int j=0; j < N; j++) alphabet[arr[j].charAt(i) - 'A']++;
			int temp = 0, idx = 0;
			for(int j=0; j < 26; j++) {
				if(temp < alphabet[j]) {
					temp = alphabet[j];
					idx = j;
				}
			}
			distance += N - temp;
			result.append((char) (idx + 'A'));
		}
		
		System.out.println(result.toString());
		System.out.println(distance);
		sc.close();
	}
}