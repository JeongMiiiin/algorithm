import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 백준 11559번 - Puyo Puyo
 * 
 * 필드에 여러가지 색깔의 뿌요를 놓는다.
 * 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올때까지 아래로 떨어진다.
 * 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 사방으로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작됨.
 * 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
 * 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 나서 다시 터짐을 반복할 때마다 1연쇄씩 늘어남.
 * 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고, 여러 그룹이 터지더라도 연쇄는 1번 추가.
 * 
 * 연쇄가 몇 번 연속으로 일어날지 계산하라.
 * 
 * 1. 맵 세팅
 * 2. 체크
 * 3. 터트리기
 * 4. 터트린 자리 메꾸기
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//맵세팅
		char[][] fields = new char[12][6];
		for(int i=11; i > -1; i--) {
			int j=0;
			for(char c : br.readLine().toCharArray()) fields[i][j++] = c;
		}
		
		int result = 0;
		while(explodeFields(fields)) result++;
		
		
		//결과 출력
		bw.write(String.valueOf(result));
		bw.close();
		br.close();
	}
	
	//상우하좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	
	private static boolean explodeFields(char[][] fields) {
		boolean result = false;
		boolean[][] visited = new boolean[12][6]; //방문처리할 배열
		boolean[][] selected = new boolean[12][6]; //선택처리할 배열
		
		//순환하기
		char cur;
		Queue<Point> curQ = new LinkedList<>();
		Queue<Point> selectQ = new LinkedList<>();
		for(int i=0; i < 12; i++) {
			for(int j=0; j < 6; j++) {
				//뿌요가 있고, 방문하지 않은 곳인 경우
				if(fields[i][j] != '.' && !visited[i][j]) {
					cur = fields[i][j];
					int curCnt = 1;
					visited[i][j] = true;
					curQ.add(new Point(i, j));
					selectQ.add(new Point(i, j));
					Point curPoint;
					while(!curQ.isEmpty()) {
						curPoint = curQ.poll();
						for(int d=0; d < 4; d++) {
							int nr = curPoint.x + dr1[d];
							int nc = curPoint.y + dc1[d];
							//맵에 벗어나지 않고, 방문하지 않고, 현재 뿌요와 동일할 때
							if(!outMap(nr, nc) && !visited[nr][nc] && fields[nr][nc] == cur) {
								curCnt++;
								visited[nr][nc] = true;
								curQ.add(new Point(nr, nc));
								selectQ.add(new Point(nr, nc));
							}
						}
					}
					//4개 이상일 떄
					if(curCnt >= 4) {
						while(!selectQ.isEmpty()) {
							curPoint = selectQ.poll();
							selected[curPoint.x][curPoint.y] = true;
							result = true;
						}
					} else selectQ.clear();
					
				}
			}
		}
		
		//삭제할 뿌요들이 있을 때
		if(result) {
			//삭제 처리
			for(int i=0; i < 12; i++) for(int j=0; j < 6; j++) if(selected[i][j]) fields[i][j] = '.';
			
			//내림 처리
			downFields(fields);
		}
		
		return result;
	}
	
	private static void downFields(char[][] fields) {
		Stack<Character> stack = new Stack<>();
		for(int i=0; i < 6; i++) {
			for(int j = 11; j > -1; j--) {
				if(fields[j][i] != '.') {
					stack.add(fields[j][i]);
					fields[j][i] = '.';
				}
			}
			int bottom = 0;
			while(!stack.isEmpty()) fields[bottom++][i] = stack.pop();
		}
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= 12 || c < 0 || c >= 6);
	}
	
}