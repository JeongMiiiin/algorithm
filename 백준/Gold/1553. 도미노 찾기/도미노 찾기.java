import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 백준 1553 - 도미노
 * 도미노의 크기는 1 x 2이고, 크기가 1x1인 칸으로 나누어져 있다. 칸은 수를 나타냄.
 * 크기가 8x7인 격자가 있고, 격자의 각 칸에는 정수가 하나씩 들어있음.
 * 0 0, 0 1, 0 2, 0 3, 0 4, 0 5, 0 6
 * 1 1, 1 2, 1 3, 1 4, 1 5, 1 6
 * 2 2, 2 3, 2 4, 2 5, 2 6
 * 3 3, 3 4, 3 5, 3 6
 * 4 4, 4 5, 4 6
 * 5 5, 5 6
 * 6 6
 * 28가지 도미노로 주어진 격자를 표현할 수 있는 경우의 수를 구하라 
 * */

public class Main {
	static int result = 0;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//맵 세팅
		map = new int[8][7];
		for(int i=0; i < 8; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) map[i][j++] = c - '0';
		}
		
		boolean[][] check = new boolean[7][7];
		boolean[][] visited = new boolean[8][7];
		find(check, visited);
		
		System.out.println(result);
		br.close();
	}
	
	private static void find(boolean[][] check, boolean[][] visited) {
		
		boolean flag = true;
		outer : for(int i=0; i < 8; i++) {
			for(int j=0; j < 7; j++) {
				if(!visited[i][j]) { //아직 방문하지 않은 곳이 있을 경우
					//가로 
					int n1 = map[i][j];
					int n2;
					if(j < 6) {
						n2 = map[i][j + 1];
						int first = Math.min(n1, n2);
						int second = Math.max(n1, n2);
						if(!check[first][second]) {
							boolean[][] tempCheck = new boolean[7][7];
							boolean[][] tempVisited = new boolean[8][7];
							copyArr(tempCheck, check);
							copyArr(tempVisited, visited);
							tempCheck[first][second] = true;
							tempVisited[i][j] = true;
							tempVisited[i][j + 1] = true;
							find(tempCheck, tempVisited);
						}
					} 
					
					//세로
					if(i < 7) {
						n2 = map[i + 1][j];
						int first = Math.min(n1, n2);
						int second = Math.max(n1, n2);
						if(!check[first][second]) {
							boolean[][] tempCheck = new boolean[7][7];
							boolean[][] tempVisited = new boolean[8][7];
							copyArr(tempCheck, check);
							copyArr(tempVisited, visited);
							tempCheck[first][second] = true;
							tempVisited[i][j] = true;
							tempVisited[i + 1][j] = true;
							find(tempCheck, tempVisited);
						}
					}
					
					flag = false;
					break outer;
				}
			}
		}
		
		if(flag) result++;
	}
	
	private static void copyArr(boolean[][] temp, boolean[][] origin) {
		for(int i=0; i < temp.length; i++) temp[i] = Arrays.copyOf(origin[i], temp[i].length);
	}
}