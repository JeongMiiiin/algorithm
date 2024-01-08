import java.util.Scanner;
/*
 * 백준 1138 - 한 줄로 서기
 * 사람의 수 N이 주어짐.
 * 자신보다 옆에 큰 사람이 얼마나 있었는지 알고 있음.
 * */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] result = new int[N];
		for(int i=0; i < N; i++) {
			int target = sc.nextInt();
			int idx = 0, temp = 0;
			while(target > temp) {
				if(result[idx++] == 0) temp++;
			}
			while(result[idx] != 0) idx++;
			result[idx] = i + 1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) sb.append(result[i] + " ");
		System.out.println(sb.toString());
		sc.close();
	}
}