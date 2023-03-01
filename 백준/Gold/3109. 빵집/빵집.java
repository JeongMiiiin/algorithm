import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 3109 빵집
 * R * C 격차
 * 첫째열은 근처 빵집의 가스관
 * 마지막 열은 원웅이의 빵집
 * 방향 - 우, 우상, 우하
 * 파이프는 첫째 열 -> 마지막 열
 * 파이프라인 경로는 겹칠 수 없고, 접할 수도 없음.
 * 시간 복잡도 -> 3의 c승 x r -> 백트래킹을 적용해야함
 * 파이프는 놓는 시도를 했을 때  성공하든 안하든 파이프를 유지시킨다 (반복 작업을 줄이기 위해)
 * 파이프를 최대로 놓을 수 있는 방향은 우상 - 우 - 우하 이다.
 * 파이프 놓은 성공을 했다면 아예 처음으로 돌아와야한다. (파이프는 동시에 둘 수 없기 때문)
*/
public class Main {
	static int R, C, ans;
	static char[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		for(int i=0; i < R; i++) map[i] = br.readLine().toCharArray();
		
		for(int i=0; i < R; i++) dfs(i, 0);
		
		System.out.println(ans);
	
	}
	
	//우상, 우, 우하를 보내주는 좌표 배열
	//열은 항상 오른쪽으로 가기 때문에 필요없음
	static int[] dr1 = {-1, 0, 1};
	static boolean dfs(int r, int c) { //r : 출발행, c : 출발열
		map[r][c] = 'x'; //방문 처리를 위한 표시 (건물로 표시해서 해당 자리를 탐색 못하게)
		if(c == C - 1) { //마지막 열에 도착 (성공)
			ans++;
			return true; //성공했다면 파이프는 중복으로 둘 수 없으니 아예 처음으로 돌아가기 위한 true값
		}
		int nr, nc = c + 1;
		for(int d=0; d < 3; d++) {
			nr = r + dr1[d]; //위, 오, 아
			if(nr < 0 || nr >= R) continue; //맵을 벗어난 경우 탐색 중지
			if(map[nr][nc] == 'x') continue; //건물을 만난 경우 탐색 중지
			
			if(dfs(nr, nc)) return true;
		}
		
		return false; //다 갔는데 성공하지 못했을 때
	}
}
