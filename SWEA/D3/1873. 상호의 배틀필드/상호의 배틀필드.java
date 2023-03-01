import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SWEA 1873 - 상호의 배틀필드
 * 배틀필드 게임 개발
 * 맵의 구성 요소
 * . : 평지 (전차 가능)
 * * : 벽돌로 만들어진 벽
 * # : 강철로 만들어진 벽
 * - : 물 (전차 X)
 * ^ : 위쪽을 바라보는 전차 (아래는 평지)
 * v : 아래쪽을 바라보는 전차 (아래는 평지)
 * < : 왼쪽을 바라보는 전차 (아래는 평지)
 * > : 오른쪽을 바라보는 전차 (아래는 평지)
 * 사용자 입력 종류
 * U : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지면 그 칸으로 이동
 * D : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 하칸 아래의 칸이 평지면 그 칸으로 이동
 * L : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지면 그 칸으로 이동
 * R : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지면 그 칸으로 이동
 * S : 전차가 현재 바라보고 있는 방향으로 포탄을 발사
 * 제약조건
 * 1. 전차는 맵 밖으로 이동할 수 없음
 * 2. 포탄은 벽돌로 만들어진 벽을 부술 수 있음.
 * 
 * 주어지는 값
 * T : 테스트 케이스 수
 * H : 맵의 높이
 * W : 맵의 너비
 * H X W : 맵의 구성요소
 * N : 수행해야 하는 입력의 개수
 * N개의 문자열 : 수행해야 하는 입력의 종류
 * 
 * 출력값 : 입력을 모두 수행한 이후의 맵의 상태를 출력
*/
public class Solution {
	static int H, W, N;
	static String[][] map;
	static int[] tankPos;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine()), t=0;
		while(t++ < T) {
			tankPos = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new String[H][W];
			for(int i=0; i < H; i++) {
				String s = br.readLine();
				for(int j=0; j < W; j++) {
					map[i][j] = s.substring(j, j+ 1);
					//전차일 때 해당 포지션 담아두기
					if(map[i][j].equals("^") || map[i][j].equals("v") || map[i][j].equals("<") || map[i][j].equals(">")) {
						tankPos[0] = i;
						tankPos[1] = j;
					}
				}
			}
			N = Integer.parseInt(br.readLine());
			
			String cmdList = br.readLine();
			
			for(int i=0; i < cmdList.length(); i++) executeCmd(cmdList.substring(i, i + 1));
			
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < H; i++) {
				for(int j=0; j < W; j++) sb.append(map[i][j]);
				sb.append("\n");
			}
			
			System.out.print("#" + t + " " + sb.toString());
		}
	}

	private static void executeCmd(String cmd) {
		//상하좌우 이동
		int[] dr1 = {-1,1,0,0};
		int[] dc1 = {0,0,-1,1};
		
		int originR = tankPos[0];
		int originC = tankPos[1];
		int r, c;
		switch(cmd) {
			case "U" : //위쪽 이동
				map[originR][originC] = "^";
				r = originR + dr1[0];
				c = originC + dc1[0];
				if(r > -1 && map[r][c].equals(".")) {
					map[r][c] = "^";
					map[originR][originC] = ".";
					tankPos[0] = r;
				}
				break;
			case "D" : //아래쪽 이동
				map[originR][originC] = "v";
				r = originR + dr1[1];
				c = originC + dc1[1];
				if(r < H && map[r][c].equals(".")) {
					map[r][c] = "v";
					map[originR][originC] = ".";
					tankPos[0] = r;
				}
				break;
			case "L" : //왼쪽 이동
				map[originR][originC] = "<";
				r = originR + dr1[2];
				c = originC + dc1[2];
				if(c > -1 && map[r][c].equals(".")) {
					map[r][c] = "<";
					map[originR][originC] = ".";
					tankPos[1] = c;
				}
				break;
			case "R" : //오른쪽 이동
				map[originR][originC] = ">"; 
				r = originR + dr1[3];
				c = originC + dc1[3];
				if(c < W && map[r][c].equals(".")) {
					map[r][c] = ">";
					map[originR][originC] = ".";
					tankPos[1] = c;
				}
				break;
			case "S" : //포탄 발사
				int dir = 0;
				if(map[originR][originC].equals("v")) {
					dir = 1;
				} else if (map[originR][originC].equals("<")) {
					dir = 2;
				} else if (map[originR][originC].equals(">")) {
					dir = 3;
				}
				r = originR + dr1[dir];
				c = originC + dc1[dir];
				while(r > -1 && r < H && c > -1 && c < W) {
					if(map[r][c].equals("#")) break;
					if(map[r][c].equals("*")) {
						map[r][c] = ".";
						break;
					}
					r += dr1[dir];
					c += dc1[dir];
				}
				break;
		}
		
	}
}
