import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1896 - 파급효과
 * 파급효과는 여러개의 폴리오미노로 구성된 직사각형의 그리드에서 발생.
 * 폴리오미노는 하나 이상의 같은 크기의 정사각형을 이어붙여 만든 도형.
 * 우리는 그리드 안의 모든 칸에 자연수를 배치해야 함.
 * 아래와 같은 규칙이 존재
 * 1. 1 ~ (폴리오미노 안의 칸 수) 의 자연수들은 해당 폴리노미오 안에 반드시 한번씩만 배치
 * 2. 하나의 열이나 행에 동일한 숫자가 배치되기 위해선 두 숫자 사이에 해당 숫자 이상의 다른 칸이 존재해야 함.
 * 파급효과가 가능한 퍼즐인지 판별하라
 * */

public class Main {
	static int R, C, max;
	static int[][] map, info;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t <= T; t++) {
			max = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			//맵 세팅
			map = new int[R][C];
			for(int i=0; i < R; i++) {
				int j = 0;
				for(char c : br.readLine().toCharArray()) {
					map[i][j] = c - '0';
					max = Math.max(map[i][j++], max);
				}
			}
			
			//폴리오미노 방향에 관한 숫자 세팅
			info = new int[R][C];
			for(int i=0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < C; j++) info[i][j] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(check() ? "valid" : "invalid");
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	//가능한 퍼즐인지 체크
	private static boolean check() {
		//행에 동일한 숫자 체크
		for(int i=0; i < R; i++) {
			for(int j=0; j < C - 1; j++) {
				int target = map[i][j];
				int diff = 0;
				for(int z= j + 1; z < C; z++) {
					if(target != map[i][z]) diff++;
					else if(diff < target) return false; //중간 숫자가 적을 때 false 리턴
				}
			}
		}
		
		//열에 동일한 숫자 체크
		for(int i=0; i < C; i++) {
			for(int j=0; j < R - 1; j++) {
				int target = map[j][i];
				int diff = 0;
				for(int z= j + 1; z < R; z++) {
					if(target != map[z][i]) diff++;
					else if(diff < target) return false; //중간 숫자가 적을 때 false 리턴
				}
			}
		}
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		boolean[][] visited = new boolean[R][C];
		boolean[] numCheck = new boolean[max + 1];
		for(int i=0; i < R; i++) {
			for(int j=0; j < C; j++) {
				//이미 방문했거나 자기자신만 폴리오미노인 경우 패스 
				if(visited[i][j] || map[i][j] == 0) continue;
				visited[i][j] = true;
				int target = map[i][j];
				Arrays.fill(numCheck, false);
				numCheck[target] = true;
				Queue<Point> q = new LinkedList<>();
				q.add(new Point(i, j));
				Point cur;
				while(!q.isEmpty()) {
					cur = q.poll();
					int dir = info[cur.x][cur.y];
					int nr, nc;
					while(dir > 0) {
						int nd = 0;
						if(dir >= 8) {
							nd = 3;
							dir -= 8;
						}
						else if(dir >= 4) {
							nd = 2;
							dir -= 4;
						}
						else if(dir >= 2) {
							nd = 1;
							dir -= 2;
						} else dir -= 1;
						
						nr = cur.x + dr1[nd];
						nc = cur.y + dc1[nd];
						if(outMap(nr, nc)) return false;
						
						if(!visited[nr][nc]) {
							if(numCheck[map[nr][nc]]) return false;
							numCheck[map[nr][nc]] = true;
							visited[nr][nc] = true;
							q.add(new Point(nr, nc));
						}
					}
				}
				
				
			}
		}
		
		return true;
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
}