import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		sc.nextLine();
		List<Integer>[] score = new ArrayList[C];
		for(int i=0; i < C; i++) score[i] = new ArrayList<>();
		for(int i=0; i < R; i++) {
			int cnt = 0;
			for(char c : sc.nextLine().toCharArray()) {
				if(c == 'S' || c == '.' || c == 'F') cnt++;
				else {
					score[cnt].add(c - '0');
					break;
				}
			}
		}
		int[] ranking = new int[10];
		int rankIdx = 1;
		for(int i= C - 1; i > 0; i--) {
			if(score[i] == null || score[i].size() == 0) continue;
			for(int num : score[i]) ranking[num] = rankIdx;
			rankIdx++;
		}
		
		for(int i=1; i <= 9; i++) System.out.println(ranking[i]);
		
		sc.close();
	}
}