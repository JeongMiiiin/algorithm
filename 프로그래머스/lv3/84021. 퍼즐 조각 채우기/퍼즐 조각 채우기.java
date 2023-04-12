import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * 프로그래머스 - 퍼즐조각채우기
 * 
 * 테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈 공간에 적절히 올려놓으려 합니다.
 * 조각은 한 번에 하나씩 채워 넣음.
 * 조각을 회전시킬 수 있음.
 * 조각을 뒤집을 수는 없음.
 * 
 * 최대한 많은 퍼즐 조각을 채워넣을 경우, 총 몇 칸을 채울 수 있는지 구하라
 * (해당 칸에 완벽하게 들어맞아야 한다.)
 * 
 * 만들어야 할 것
 * 1. 4개의 회전 table (만들 때 고유 번호로 바꿔주기)
 * 2. 사용한 고유번호들은 뒤에서 사용 못하도록 하기
 * 
*/

class Solution {
    static class Block {
		//angle -> 0 : 0도, 1 : 90도, 2 : 180도, 3 : 270도
		int no, size, angle, r, c;

		public Block(int no, int size, int angle, int r, int c) {
			this.no = no;
			this.size = size;
			this.angle = angle;
			this.r = r;
			this.c = c;
		}
	}
	
	//상 우 하 좌
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, 1, 0, -1};
	
	static Map<Integer, Integer> blocksSize = new HashMap<>();
	static Queue<Block> blocks = new LinkedList<>();
	static int[][] table0, table1, table2, table3;
	static boolean[] blockUsed;
	static int blockIdx, N;
    
    public int solution(int[][] game_board, int[][] table) {
        //초기화
		N = table.length;
		blockIdx = 2;
		
		table0 = new int[N][N];
		
		makeBlockVisited = new boolean[N][N];
		//블록 표시
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) if(!makeBlockVisited[i][j] && table[i][j] == 1) makeBlock(table, table0, i, j);
		
		//테이블 90도 돌린 것
		table1 = new int[N][N];
		turnMap90(table0, table1);
		
		updateBlock(table1, 1);
		
		//테이블 180도 돌린 것
		table2 = new int[N][N];
		turnMap90(table1, table2);
		
		updateBlock(table2, 2);
		
		//테이블 270도 돌린 것
		table3 = new int[N][N];
		turnMap90(table2, table3);
		
		updateBlock(table3, 3);
		
		//사이즈 다 사용했으니 초기화
		blocksSize.clear();
		
		//사용한 블록 표시하기 위한 boolean 배열
		blockUsed = new boolean[blockIdx + 1];
		
		//체크하기
		for(int i=0; i < N; i++)
			for(int j=0; j < N; j++)
				if(game_board[i][j] == 0) matchingBlock(game_board, i, j);
		
		//채운 칸 세기
		int ans = 0;
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) if(game_board[i][j] > 1) ans++;
		
        return ans;
    }
    
    //가지고 있는 블록 다 매칭해보기
	private static void matchingBlock(int[][] game_board, int r, int c) {
		int blocksLen = blocks.size();
		
		int[][] testBoard = new int[N][N];
		copyBoard(game_board, testBoard);
		
		Block cur;
		for(int i=0; i < blocksLen; i++) {
			cur = blocks.poll();
			//사용한 블록인 경우 패스
			if(blockUsed[cur.no]) continue;
			
			//들어가는걸 확인했을 때
			if(check(testBoard, r, c, cur)) {
				blockUsed[cur.no] = true;
				copyBoard(testBoard, game_board);
				break;
			} else { //안 들어갈 때는 원복
				copyBoard(game_board, testBoard);
				blocks.offer(cur);
			}
		}
		
		
	}
	
	private static boolean check(int[][] testBoard, int r, int c, Block block) {
		
		int size = 1;
		testBoard[r][c] = block.no;
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r, c));
		
		int blockR = block.r;
		int blockC = block.c;
		
		//비교할 블록이 있는 곳
		int[][] compareTable = table0;
		switch(block.angle) {
		case 1 :
			compareTable = table1;
			break;
		case 2 :
			compareTable = table2;
			break;
		case 3 :
			compareTable = table3;
			break;
		}
		
		boolean flag = true;
		
		int nr, nc;
		Point cur;
		while(!q.isEmpty()) {
			
			cur = q.poll();
			for(int d=0; d < 4; d++) {
				nr = cur.x + dr1[d];
				nc = cur.y + dc1[d];
				//맵 벗어나거나 채워진 칸을 만났을 때 패스
				if(outMap(nr, nc) || testBoard[nr][nc] == 1) continue; 
				
				//차이 계산
				int compareR = r - nr;
				int compareC = c - nc;
				
				//빈 칸인 경우
				if(testBoard[nr][nc] == 0) {
					//블록이 들어갈 수 있을 때
					if(!outMap(blockR - compareR, blockC - compareC) && compareTable[blockR - compareR][blockC - compareC] == block.no) {
						testBoard[nr][nc] = block.no;
						size++;
						q.offer(new Point(nr, nc));
					} else {
						flag = false;
						break;
					}
				}
			}
		}
		
		//사이즈가 다를때에 맞지 않다고 판단
		if(size != block.size) flag = false;
		
		return flag;
	}

	//게시판 복사
	private static void copyBoard(int[][] originBoard, int[][] testBoard) {
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) testBoard[i][j] = originBoard[i][j];
	}

	//회전된 블록 추가해주기
	private static void updateBlock(int[][] table, int angle) {
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				//블록이 있을 때
				if(table[i][j] > 0) blocks.offer(new Block(table[i][j], blocksSize.get(table[i][j]), angle, i, j));
			}
		}
		
	}

	static boolean[][] makeBlockVisited;
	
	private static void makeBlock(int[][] originTable, int[][] copyTable, int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(r, c));
		makeBlockVisited[r][c] = true;
		copyTable[r][c] = blockIdx;
		
		int blockSize = 1;
		
		int nr, nc;
		Point cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			for(int d=0; d < 4; d++) {
				nr = cur.x + dr1[d];
				nc = cur.y + dc1[d];
				//맵에서 벗어나지 않고, 방문하지 않았고, 같은 블록의 경우
				if(!outMap(nr, nc) && !makeBlockVisited[nr][nc] && originTable[nr][nc] == 1) {
					blockSize++;
					copyTable[nr][nc] = blockIdx;
					makeBlockVisited[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
		}
		
		//사이즈 담아두기
		blocksSize.put(blockIdx, blockSize);
		
		//해당 블록 추가해놓기
		blocks.offer(new Block(blockIdx++, blockSize, 0, r, c));
	}
	
	//맵 90도 돌리기
	private static void turnMap90(int[][] originTable, int[][] copyTable) {
		for(int i=0; i < N; i++) for(int j=0; j < N; j++) copyTable[N - 1 - j][i] = originTable[i][j];
	}
	
	//맵 벗어나는지 확인
	private static boolean outMap(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
}