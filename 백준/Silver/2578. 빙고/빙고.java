import java.util.Scanner;

/*
 * 백준 2578 - 빙고
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] map = new int[5][5];
		for(int i=0; i < 5; i++) for(int j=0; j < 5; j++) map[i][j] = sc.nextInt();
		
		int idx = 0;
		int cnt = 0;
		while(cnt < 3) {
			idx++;
			int target = sc.nextInt();
			
			//지워야할 수 지우기
			outer : for(int i=0; i < 5; i++) {
				for(int j=0; j < 5; j++) {
					if(map[i][j] == target) {
						map[i][j] = 0;
						break outer;
					}
				}
			}
			
			int total;
			//가로 찾기
			for(int i=0; i < 5; i++) {
				total = 0;
				for(int j=0; j < 5; j++) total += map[i][j];
				
				if(total == 0) cnt++;
			}
			
			//세로 찾기
			for(int i=0; i < 5; i++) {
				total = 0;
				for(int j=0; j < 5; j++) total += map[j][i];
				
				if(total == 0) cnt++;
			}
			
			//오른쪽 대각선 찾기
			total = 0;
			for(int i=0; i < 5; i++) total += map[i][i];
			if(total == 0) cnt++;
			
			//왼쪽 대각선 찾기
			total = 0;
			for(int i=0; i < 5; i++) total += map[i][4 - i];
			if(total == 0) cnt++;
			
			//3개 이상이면 끝
			if(cnt > 2) break;
			else cnt = 0;
		}
		System.out.println(idx);
		sc.close();
	}
}