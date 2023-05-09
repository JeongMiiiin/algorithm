import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 백준 7682 - 틱택토
 * 
 * 두 명의 사람이 번갈아가며 말을 놓는 게임
 * 게임판은 3 x 3 격자판이며 처음에는 비어있다.
 * 두 사람은 각각 X 또는 O 말을 번갈아가며 놓는데,
 * 반드시 첫 번째 사람이 X를 놓고, 두 번째 사람이 O를 놓는다.
 * 어느 때든지 한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는데
 * 성공하면 게임은 즉시 끝남.
 * 게임판이 가득 차도 게임은 끝남.
 * 
 * 입력의 마지막에는 end가 주어짐
 * 
 * 가능한 상황
 * 1. 가득찼을 경우에는 X가 O보다 하나 많아야 하며 줄이 완성되서는 안됨.
 * 2. 빈칸이 있을 경우 줄은 하나만 완성되어 있어야 하며 X가 O보다 하나 많아야 함.
 * 
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {
		String s = br.readLine();
		while(!s.equals("end")) {
			if(tictactoc(s)) bw.write("valid\n");
			else bw.write("invalid\n");
			s = br.readLine();
		}
		
		br.close();
		bw.close();
	}
	
	static char[][] map;
	public static boolean tictactoc(String s) {
		map = new char[3][3];
		
		char[] cList = s.toCharArray();
		int XCnt = 0;
		int OCnt = 0;
		boolean full = true;
		for(int i=0; i < 9; i++) {
			map[i / 3][i % 3] = cList[i];
			if(map[i / 3][i % 3] == 'X') XCnt++;
			if(map[i / 3][i % 3] == 'O') OCnt++;
			if(map[i / 3][i % 3] == '.') full = false;
		}
		
		//O가 더 많을 때 
		if(XCnt < OCnt) return false;
		
		//꽉차있는 경우
		if(full && XCnt == OCnt + 1) { 
			if(isConnect('O') && !isConnect('X')) return false;
			else if(isConnect('O') && isConnect('X')) return false;
			
			return true;
		}
		
		if(!full) {
			if(XCnt == OCnt + 1) {
				if(isConnect('X') && !isConnect('O')) return true;
				else return false;
			} else if(XCnt == OCnt) {
				if(isConnect('O') && !isConnect('X')) return true;
				else return false;
			}
		}
		
		return false;
	}
	
	private static boolean isConnect(char c) {
		// 가로
		for(int i=0; i<3; i++) {
			if(map[i][0] == c && map[i][1] == c && map[i][2] == c) return true;
		}
		// 세로 
		for(int i=0; i<3; i++) {
			if(map[0][i] == c && map[1][i] == c && map[2][i] == c) return true;
		}
		// 대각선 
		if(map[0][0] == c && map[1][1] == c && map[2][2] == c) return true;
		if(map[2][0] == c && map[1][1] == c && map[0][2] == c) return true;
		
		return false;
	}
}