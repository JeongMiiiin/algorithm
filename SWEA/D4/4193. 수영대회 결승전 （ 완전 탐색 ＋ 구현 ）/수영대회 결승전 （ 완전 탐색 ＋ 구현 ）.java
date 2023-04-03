import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 4193 - 수영대회 결승전
 * 
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 맵의 크기
 * 3번째 줄 - N번째 줄 : 맵의 상태
 * N + 1 번째 줄 : 시작지점
 * N + 2 번째 줄 : 도착지점
 * 
 * N x N의 맵 사용
 * 맵을 벗어나면 안됨
 * 맵의 종류
 * 0 : 빈 칸
 * 1 : 지나갈 수 없는 장애물
 * 2 : 주기적으로 생기는 소용돌이
 * 소용돌이는 2초 생성 후 1초동안 잠잠
*/
public class Solution {
	static class Person {
		int r, c;

		public Person(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			//맵 세팅
			int[][] map = new int[N][N];
			StringTokenizer st;
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken()); 
			
			st = new StringTokenizer(br.readLine(), " ");
			int endR = Integer.parseInt(st.nextToken());
			int endC = Integer.parseInt(st.nextToken());
			
			//맵 세팅
			boolean[][] visited = new boolean[N][N];
			
			Person p = new Person(startR, startC);
			
			Queue<Person> q = new LinkedList<>();
			q.offer(p);
			visited[p.r][p.c] = true;
			
			//상 우 하 좌
			int[] dr1 = {-1, 0, 1, 0};
			int[] dc1 = {0, 1, 0, -1};
			
			int time = 0;
			boolean flag = false;
			outer : while(!q.isEmpty()) {
				int size = q.size();
				for(int i=0; i < size; i++) {
					Person cur = q.poll();
					//찾았으면 종료
					if(cur.r == endR && cur.c == endC) {
						flag = true;
						break outer;
					}
					
					int nr, nc;
					for(int d=0; d < 4; d++) {
						nr = cur.r + dr1[d];
						nc = cur.c + dc1[d];
						//맵을 벗어나거나 섬을 만난 경우 패스
						if(outMap(nr, nc) || map[nr][nc] == 1) continue;
						//활성화된 소용돌이 만났을 경우 제 자리
						if(map[nr][nc] == 2 && time % 3 != 2) q.offer(cur);
						//해당 자리로 이동 가능한 경우
						else if( !visited[nr][nc] ) {
							visited[nr][nc] = true;
							q.offer(new Person(nr, nc));
						}
						
					}
				}
				
				time++;
			}
			
			//도착 못하는 경우
			if(!flag) time = -1;
			
			//출력
			bw.write("#" + t + " " + time + "\n");
		}
			
		bw.close();
	}

	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
	
}