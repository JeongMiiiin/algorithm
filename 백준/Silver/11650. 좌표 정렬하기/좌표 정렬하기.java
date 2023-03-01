import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//백준 11650번 좌표 정렬하기
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] map = new int[N][2];
		for(int i=0; i < N; i++) {
			map[i][0] = sc.nextInt();
			map[i][1] = sc.nextInt();
		}
		
		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] n1, int[] n2) {
				if(n1[0] == n2[0]) {
					return n1[1] - n2[1];
				} else {
					return n1[0] - n2[0];
				}
			}
		});
		
		for(int i=0; i < N; i++)
			System.out.println(map[i][0] + " " + map[i][1]);
		
	}
}
