import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
 * 백준 27958 - 사격연습
 * 
 * 주어지는 값
 * N : 보드판의 크기
 * K : 사격 횟수
 * 셋째줄 ~ N + 2번째 줄 : 보드판의 정보
 * N + 3번째 줄 : 총알 공격력의 정보
 * 
 * N x N 크기의 보드판 존재
 * 1개 이상의 표적이 존재
 * 한 학생은 사격을 연습, 1 ~ K번까지 K개의 총알을 가지고 있음.
 * 총 K번의 사격을 진행하며, 1번부터 K번까지의 총알을 차례대로 발사
 * 한 번의 사격을 할 때는 1행부터 N행까지 중에서 하나의 행을 선택하여 사격을 진행
 * 총알은 가장 왼쪽열에서 시작하여 왼쪽에서 오른쪽으로 수평으로 한 칸씩 이동하며 날아감.
 * 각 총알은 공격력 정보를 가지고 있음.
 * 표적이 있는 위치는 1 이상의 자연수로 표시되며, 표적이 없는 곳은 정수 0으로 표시
 * 해당 값은 초기 체력을 의미
 * 총알이 표적에 닿으면 현재 체력이 총알의 공격력만큼 감소.
 * 총알은 표적을 관통하지 못하고, 총알은 사라짐.
 * 총알에 닿아 현재 체력이 0 이하가 되면 표적은 사라짐
 * 사라지면 일어나는 상황
 * 1. 해당 표적의 초기 체력만큼 점수를 얻음.
 * 2. 상하좌우에 초기 체력의 1/4 만큼의 초기 체력을 갖는 새로운 표적 생성
 *    (소수점은 버리며, 그 값이 0인 경우에 표적 생성 X)
 * 초기 체력이 10 이상인 경우에 보너스 표적으로
 * 별도의 초기체력이 없어서 총알에 닿는 순간 사라지며
 * 보너스 표적에 적힌 점수만큼 얻을 수 있고, 상하좌우에 새로운 표적을 생성 X
 * 얻을 수 있는 점수의 최댓값을 출력하라 
 *
 * 중복순열으로 총알을 쏠 곳을 정하고 시뮬레이션으로 쏘자
 *
 * 
*/
public class Main {
	
	//표적을 관리하는 클래스
	static class Target{
		int r, c, HP, remainHP;
		boolean bonus = false;
		public Target(int r, int c, int HP) {
			this.r = r;
			this.c = c;
			this.HP = HP;
			this.remainHP = HP;
			if(this.HP > 9) this.bonus = true;
		}
	}
	
	static int N, K, ans = 0;
	static int[] bullets; //총알의 공격력을 담기 위한 배열
	static int[] numbers; //조합 시에 사용
	static Map<Integer, Target> targetList = new HashMap<>(); //초기 표적들을 담을 해시맵 (시뮬레이션 시에 복사해서 사용)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		bullets = new int[K];
		numbers = new int[K];
		
		//맵의 타겟 정보 받기
		StringTokenizer st;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j < N; j++) {
				int mapInfo = Integer.parseInt(st.nextToken());
				//타겟이 만들어져야 하는 경우
				if(mapInfo > 0) makeTarget(targetList, i, j, mapInfo);
			}
		}
		
		//총알 정보 받기
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < K; i++) bullets[i] = Integer.parseInt(st.nextToken());
		
		dupliPerm(0);
		
		//출력
		System.out.println(ans);
	}
	
	private static void dupliPerm(int cnt) {
		if(cnt == K) { //중복순열이 완성되면 시뮬레이션 실행
			shotAction();
			return;
		}
		
		for(int i=0; i < N; i++) {
			numbers[cnt] = i;
			dupliPerm(cnt + 1);
		}
	}

	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	private static void shotAction() {
		int points = 0;
		
		//시뮬레이션을 위해 깊은 복사
		Map<Integer,Target> testTarget = new HashMap<>();
		for(Entry<Integer, Target> pair : targetList.entrySet()) {
			testTarget.put(pair.getKey(), new Target(pair.getValue().r, pair.getValue().c, pair.getValue().HP));
		}
		
		for(int i=0; i < K; i++) {
			Target t = findTarget(testTarget, numbers[i]);
			if(t != null) { //타겟이 있을 때
				if( !t.bonus ) { //보너스 타겟이 아닐 때 
					if(t.remainHP <= bullets[i]) { //사라지게 할 때
						points += t.HP; //점수 더해주기
						int newPoints = t.HP / 4;
						if(newPoints > 0) { //새로운 표적을 생성해야할 때
							int nr, nc;
							for(int d=0; d < 4; d++) {
								nr = t.r + dr1[d];
								nc = t.c + dc1[d];
								//맵에 벗어나지 않고 이미 표적이 있지 않으면 생성
								if(!outMap(nr, nc) && !checkMap(testTarget, nr, nc)) makeTarget(testTarget, nr, nc, newPoints);
							}
						}
						removeTarget(testTarget, t.r, t.c); //타겟 삭제
					} else t.remainHP -= bullets[i];
				} else { //보너스 타겟일 때
					points += t.HP; //점수 더해주기
					removeTarget(testTarget, t.r, t.c); //타겟 삭제
				}
			}
		}
		
		ans = Math.max(ans, points);
	}
	
	//해당 자리에 표적이 있는지 확인
	private static boolean checkMap(Map<Integer, Target> testTarget, int r, int c) {
		return testTarget.get((r * N) + c) != null;
	}

	//지정한 행의 타겟 찾기
	private static Target findTarget(Map<Integer, Target> testTarget, int r) {
		//타겟 찾기
		for(int i=0; i < N; i++) {
			//타겟을 찾으면 돌려주기
			if(testTarget.get((r * N) + i) != null) return testTarget.get((r * N) + i);
		}
		//없으면 null 전달
		return null;
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
	

	//타겟을 만들어서 리스트에 추가하는 함수
	private static void makeTarget(Map<Integer, Target> testTarget, int r, int c, int hp) {
		testTarget.put((r * N) + c, new Target(r, c, hp));
	}
	
	private static void removeTarget(Map<Integer, Target> testTarget, int r, int c) {
		testTarget.remove((r * N) + c);
	}
}