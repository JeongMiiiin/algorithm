import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * 시뮬레이션 순서
 * 1. 적들의 위치를 저장
 * 2. 궁수들 위치 선정 (조합 - 3명) : 3명이 고정되어 있으므로 반복문 활용
 * 3. 적들을 처치하자
 * 	  3-1. 적들의 위치를 복사
 * 	  3-2. 선택된 궁수의 위치를 기반으로 적들을 모두 처치하자
 *    	   3-2-1. 선택된 궁수의 위치와 가장 가까운 적을 찾아서 처치
 *    			- 지정된 거리를 넘지 않아야 한다.
 *    			- 가장 가까운 적을 찾는다. (단, 여러명인 경우 왼쪽의 적을 선택)
 *    	   3-2-2. 적을 처치 후 적들을 한줄 내린다.
 *    			- 내린 적이 성에 도달하면 (N) 적을 제거
 *    	   3-2-3. 현재 조합에서 처치된 적과 이전 조합에서 처치된 적들의 수 중에 가장 큰 수를 저장
 * 4. 처치한 적의 수를 출력
*/
public class Main {
	static class Enemy {
		int r, c;
		public Enemy(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	static int N, M, D, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		
		// 1. 적들의 위치를 저장
		List<Enemy> enemyList = new LinkedList<>();
		for(int i=0; i < N; i++) for(int j=0; j < M; j++) if(sc.nextInt() == 1) enemyList.add(new Enemy(i, j));
			
		// 2. 궁수의 위치를 선정(조합)
		for(int i=0; i < M - 2; i++) {
			for(int j= i + 1; j <M - 1; j++) {
				for(int k= j + 1; k < M; k++) {
					List<Enemy> copyEnemies = new LinkedList<>();
					for(Enemy e : enemyList) copyEnemies.add(new Enemy(e.r, e.c));
					
					//궁수의 위치에서 적들을 모두 처치하자
					killEnemies(copyEnemies, new int[] {i,j,k});
				}
			}
		}

		
		
		//출력
		System.out.println(ans);
		sc.close();
	}
	private static void killEnemies(List<Enemy> copyEnemies, int[] archers) {
		int cnt = 0;
		
		while( !copyEnemies.isEmpty() ) {
			Set<Enemy> removeEnemies = new HashSet<>();
			for(int archer : archers) {
				Enemy e = findEnemy(copyEnemies, archer);
				//적을 찾음
				if(e != null) removeEnemies.add(e);
			}
			//현재 턴에서 죽인 적들을 카운트
			cnt += removeEnemies.size();
			//죽인 적들을 List에서 제거
			copyEnemies.removeAll(removeEnemies);
			//적들을 아래로 이동
			downEnemy(copyEnemies);
			
		}
		
		ans = Math.max(ans, cnt);
	}
	private static void downEnemy(List<Enemy> copyEnemies) {
		//리스트를 반복하며 적의 r의 좌표를 증가시킨다.
		//단, 증가시킨 좌표가 N과 같다면 적은 제거 (성을 만났기 때문)
		Iterator<Enemy> ite = copyEnemies.iterator();
		while(ite.hasNext()) {
			Enemy e = ite.next();
			e.r++;
			if(e.r == N) ite.remove();
		}
	}
	private static Enemy findEnemy(List<Enemy> copyEnemies, int archer) {
		int minD = 1 << 30, minC = 50;
		Enemy find = null;
		
		for(Enemy e : copyEnemies) {
			int d =(N - e.r) + Math.abs(archer - e.c); //거리
			if(d > D || d > minD) continue; //거리를 벗어났거나 혹은 찾은 최소 거리보다 클 때는 패스
			
			//새롭게 찾은 적과의 거리가 기존에 찾은 적과의 거리보다 적을 때

			if(d < minD) { //기존거리보다 적을 때
				minD = d;
				minC = e.c;
				find = e;
				continue;
			}
			
			//새로게 찾은 적의 거리가 현재의 최소거리와 같다.
			if(minC > e.c) {
				minC = e.c;
				find = e;
			}
			
		}
		
		return find;
	}
}
