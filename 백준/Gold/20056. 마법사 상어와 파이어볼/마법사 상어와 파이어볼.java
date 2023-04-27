import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 20056 - 마법사 상어와 파이어볼
 *
 * 주어지는 값
 * N : 맵 가로 크기
 * M : 맵 세로 크기
 * K : 이동 명령
 * 2 ~ M개의 줄 : 파이어볼 정보(r, c, m, s, d)
 * 
 * 어른상어가 마법사가 되었고, 파이어볼을 배웠다.
 * 마법사 상어가 크기가 N x N인 격자에 파이어볼 M개를 발사했다.
 * 가장 처음 파이어볼은 각자 위치에서 이동을 대기하고 있다.
 * i번 파이어볼 위치는 (r,c), 질량은 m이며 방향은 d, 속력은 s이다.
 * 격자의 행과 열은 1 ~ N으로 되어있고, 각 행과 열은 연결되어 있음.
 * 파이어볼에게 이동을 명령하면 다음의 일들이 일어남
 * 1. 모든 파이어볼이 자신의 방향 d로 속력 s만큼 이동 (이동 중에는 같은 칸에 여러개의 파이어볼이 있을 수 있음)
 * 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어남
 * 2-1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐짐
 * 2-2. 파이어볼은 4개의 파이어볼로 나뉨
 * 2-3. 나뉜 파이어볼의 질량, 속력, 방향은 다음과 같음
 * 2-4. 질량은 (합쳐진 파이어볼의 질량 합) / 5, 속력은 (합쳐진 파이어볼 속력 합) / (합쳐진 파이어볼 개수)
 * 2-5. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면 방향은 0, 2, 4, 6 이고, 그렇지 않으면 1, 3, 5, 7
 * 2-6. 질량이 0인 파이어볼은 소멸되어 없어짐.
 * 
 * 이동을 K번 한 후에 남아있는 파이어볼의 질량의 합을 출력
 * 
 *  만들어야할 것
 *  1. 파이어볼 클래스
 *  2. 좌표 Queue HashMap
 *  
*/
public class Main {
	static class FireBall {
		int r, c, m, s, d;

		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<FireBall> balls = new LinkedList<>();
		
		//초기 파이어볼 세팅
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			balls.add(new FireBall(r, c, m, s, d));
		}
		
		//상 우상 우 우하 하 좌하 좌 좌상
		int[] dr1 = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dc1 = {0, 1, 1, 1, 0, -1, -1, -1};
		
		Map<Integer, Queue<FireBall>> points = new HashMap<>();
		
		//이동명령 수행
		for(int i=0; i < K; i++) {
			int ballSize = balls.size();
			FireBall cur;
			for(int j=0; j < ballSize; j++) {
				cur = balls.poll();
				int nr = cur.r + (dr1[cur.d] * cur.s);
				int nc = cur.c + (dc1[cur.d] * cur.s);
				
				if(nr > N) {
					int share = nr / N;
					nr -= (N * share);
				}
				
				if(nc > N) {
					int share = nc / N;
					nc -= (N * share);
				}
				
				//더 작게 나온 경우
				if(nr < 1) {
					int share = (-nr) / N != 0 ? ((-nr) / N) : 0;
					share++;
					nr += (N * share);
				}
				if(nc < 1) {
					int share = (-nc) / N != 0 ? (-nc) / N : 0;
					share++;
					nc += (N * share);
				}
				
				//계산하고 좌표에 넣기
				if(points.get(nr * 50 + nc) == null) points.put(nr * 50 + nc, new LinkedList<>());
				
				points.get(nr * 50 + nc).add(new FireBall(nr, nc, cur.m, cur.s, cur.d));
				
			}
			
			//같은 곳에 두개 이상이면 4개로 나누기
			for(Entry<Integer, Queue<FireBall>> pair : points.entrySet()) {
				if(pair.getValue().size() > 1) {
					int curSize = pair.getValue().size();
					Queue<FireBall> temp = pair.getValue();
					cur = temp.poll();
					int r = cur.r;
					int c = cur.c;
					int weight = cur.m;
					int speed = cur.s;
					int dir = cur.d % 2;
					boolean flag = true;
					while( !temp.isEmpty() ) {
						cur = temp.poll();
						weight += cur.m;
						speed += cur.s;
						if(cur.d % 2 != dir) flag = false;
					}
					
					//나눈 질량이 0보다 큰 경우
					if(weight / 5 >= 1) {
						weight /= 5;
						speed /= curSize;
						int[] nDir = new int[] {1,3,5,7};
						if(flag) nDir = new int[] {0,2,4,6};
						for(int z=0; z < 4; z++) balls.add(new FireBall(r, c, weight, speed, nDir[z])); 
					}
					
				} else balls.add(pair.getValue().poll());
			}
			
			
			points.clear();
		}
		
		int ans = 0;
		while(!balls.isEmpty()) ans += balls.poll().m;
		
		//출력
		System.out.println(ans);
	}
}