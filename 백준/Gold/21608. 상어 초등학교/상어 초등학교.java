import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 백준 21608 - 상어 초등학교
 * 
 * 교실은 N x N 크기의 격자
 * 학교에 다니는 학생은 N2
 * 학생은 1 ~ N2 까지 번호가 매겨져 있음
 * (r,c)는 r행 c열
 * 1,1 ~ n,n까지 존재
 * 자리 배정의 학생 순서는 정해져 있으며, 각 학생이 좋아하는 학생 4명 존재
 * 다음과 같은 규칙으로 학생 자리 배정
 * 인접하는 조건 -> Math.abs(r1 - r2) + Math.abs(c1 - c2) = 1 (상 우 하 좌)
 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
 * 2. 1을 만족하는 칸이 여러개면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
 * 3. 2를 만족하는 칸도 여러개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러개면 열의 번호가 가장 작은 칸으로 자리를 정한다.
 * 
*/
public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		
		//상 우 하 좌
		int[] dr1 = {-1, 0, 1, 0};
		int[] dc1 = {0, 1, 0, -1};
		
		List<Integer>[] favoriteList = new ArrayList[N * N + 1];
		
		//자리잡기
		int student, correctR = 0, correctC = 0;
		Set<Integer> favorite = new HashSet<>();
		StringTokenizer st;
		for(int i=0;  i < N * N; i++) {
			//초기화
			correctR = 0;
			correctC = 0;
			favorite.clear();
			st = new StringTokenizer(br.readLine());
			//배정해야할 학생 값 받기
			student = Integer.parseInt(st.nextToken());
			favoriteList[student] = new ArrayList<>();
			for(int j=0; j < 4; j++) {
				favoriteList[student].add(Integer.parseInt(st.nextToken())); 
				favorite.add(favoriteList[student].get(j));
			}
			
			int totalFavoriteCnt = 0, totalEmptyCnt = 0;
			int nr, nc, favoriteCnt, emptyCnt;
			for(int y=1; y <= N; y++) {
				for(int x=1; x <= N; x++) {
					if(map[y][x] != 0) continue; //비어있는 칸이 아닌 경우 패스
					favoriteCnt = 0;
					emptyCnt = 0;
					for(int d=0; d < 4; d++) {
						nr = y + dr1[d];
						nc = x + dc1[d];
						if(outMap(nr, nc)) continue; //맵에서 벗어난 경우
						
						if(map[nr][nc] == 0) emptyCnt++; //비어있는 칸인 경우
						if(favorite.contains(map[nr][nc])) favoriteCnt++; //좋아하는 학생인 경우 
					}
					
					if(totalFavoriteCnt < favoriteCnt) { //좋아하는 학생이 가장 많은 칸에 해당할 때
						correctR = y;
						correctC = x;
						totalFavoriteCnt = favoriteCnt;
						totalEmptyCnt = emptyCnt;
					} else if(totalFavoriteCnt == favoriteCnt && totalEmptyCnt < emptyCnt) {
						correctR = y;
						correctC = x;
						totalEmptyCnt = emptyCnt;
					} else if(totalEmptyCnt == emptyCnt && correctR == 0 && correctC == 0) {
						correctR = y;
						correctC = x;
					}
				}
			}
			
			//배정
			map[correctR][correctC] = student;
			
		}
		
		int score = 0;
		for(int i=1; i <= N; i++) {
			for(int j=1; j <= N; j++) {
				int favoriteCnt = 0, nr, nc;
				for(int d=0; d < 4; d++) {
					nr = i + dr1[d];
					nc = j + dc1[d];
					if(outMap(nr, nc)) continue;
					if(favoriteList[map[i][j]].contains(map[nr][nc])) favoriteCnt++;
				}
				if(favoriteCnt > 0) score += Math.pow(10, favoriteCnt - 1);
			}
		}
		
		System.out.println(score);
		
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 1 || r > N || c < 1 || c > N);
	}
}