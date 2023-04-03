import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 9205 - 맥주 마시면서 걸어가기
 * 
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 편의점 개수
 * N + 2개 줄 : 상근이네, 편의점, 펜타포트 락 페스티벌 좌표가 주어짐.
 * 두 좌표 사이의 거리 dir = Math.abs(x1 - x2) + Math.abs(y1 - y2);
 * 
 * 출발은 상근이네 집
 * 맥주 한 박스를 들고 출발
 * 한 박스는 최대 20개
 * 맥주 한 박스에는 맥주가 20개 들어있음
 * 목이 마르면 안되기에 50m에 한 병씩 마심 -> 50m를 가려면 그 직전에 맥주 한 병을 마셔야 함
 * 
 * 페스티벌까지 가는동안 맥주를 더 구매해야 할 수도 있음.
 * 편의점이 있으나 편의점에서는 빈 병과 같은 개수만 구매 가능
 * 또한 편의점에 들리면 나온 직후 맥주 한병을 마셔야 함
 * 
 * 편의점, 상근이네 집, 페스티벌의 좌표가 주어질 때
 * 행복하게 페스티벌에 도착할 수 있는지 구하는 프로그램을 작성
 * "happy"나 "sad" 출력
*/
public class Main {
	static class Person {
		int r, c, beerCnt;

		public Person(int r, int c, int beerCnt) {
			this.r = r;
			this.c = c;
			this.beerCnt = beerCnt;
		}
	}
	
	static class ConvenienceStore {
		int r, c;
		public ConvenienceStore(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	/*int[][] cost = new int[N + 2][N + 2];
	boolean[][] possible = new boolean[N + 2][N + 2];
	*/
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t <= T; t++) {
			int N = sc.nextInt(); //편의점 개수
			
			Queue<Person> q = new LinkedList<>();
			
			//시작점 담기
			q.offer(new Person(sc.nextInt(), sc.nextInt(), 20));
			
			ConvenienceStore[] storeList = new ConvenienceStore[N];
			boolean[] visited = new boolean[N];
			
			for(int i=0; i < N; i++) storeList[i] = new ConvenienceStore(sc.nextInt(), sc.nextInt());
			
			int[] festival = {sc.nextInt(), sc.nextInt()};
			
			boolean flag = false;
			
			Person cur;
			while(!q.isEmpty()) {
				cur = q.poll();
				//현재 위치에서 페스티벌로 갈 수 있으면 가능하다고 표시하고 while문 종료
				if(possibleGo(cur.r, cur.c, festival[0], festival[1], cur.beerCnt)) {
					flag = true;
					break;
				}
				
				for(int i=0; i < N; i++) {
					if(visited[i]) continue; //이미 지나온 편의점이면 통과
					if(possibleGo(cur.r, cur.c, storeList[i].r, storeList[i].c, cur.beerCnt)) {
						visited[i] = true;
						q.offer(new Person(storeList[i].r, storeList[i].c, 20));
					}
				}
			}
			
			String ans = "sad";
			if(flag) ans = "happy";
			
			//출력
			System.out.println(ans);
		}
		
		sc.close();
	}

	private static boolean possibleGo(int fromR, int fromC, int toR, int toC, int beerCnt) {
		int dir = Math.abs(fromR - toR) + Math.abs(fromC - toC);
		
		return (dir <= (beerCnt * 50) );
	}
}