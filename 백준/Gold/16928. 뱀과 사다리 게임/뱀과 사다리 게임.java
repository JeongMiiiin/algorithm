import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 16928 - 뱀과 사다리 게임
 * 
 * 주어지는 값
 * N : 사다리의 수
 * M : 뱀의 수
 * N개의 줄 : 사다리의 정보 x, y
 * M개의 줄 : 뱀의 정보 x, y
 * x칸에 위치하며, x칸에 도착 시 y로 이동
 * 
 * 게임은 정육면체 주사위를 사용하며, 주사위의 각 면에는 1부터 6까지 수가 하나씩 적혀있다.
 * 맵 크기는 10 x 10
 * 맵에는 1부터 100까지 수가 하나씩 순서대로 적혀져 있다.
 * 플레이어는 주사위를 굴려 나온 수만큼 이동해야 함.
 * 주사위를 굴린 결과가 100번 칸을 넘어가게 되면 이동할 수 없음.
 * 도착한 칸이 사다리면 사다리를 타고 위로 올라감.
 * 도착한 칸이 뱀이면 뱀을 따라 내려감
 * 게임의 목표는 1번 칸에서 100번 칸에 도착하는 것.
 * 게임판의 상태가 주어졌을 때, 100번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수의 최솟값
 * 
 * 6가지의 상태를 보내고, cnt가 현재 값보다 작으면 백트래킹?
 * 
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static int[] ladderMap = new int[101];
	static boolean[] ladderVisited = new boolean[101];
	static int[] snakeMap = new int[101];
	static int[] map = new int[101];
	static boolean[] snakeVisited = new boolean[101];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Arrays.fill(map, Integer.MAX_VALUE);
		
		//사다리 세팅
		int start, end;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			ladderMap[start] = end;
		}
		
		//뱀 세팅
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			snakeMap[start] = end;
		}
		
		simulate(1,0);
		
		
		//출력
		System.out.println(map[100]);
	}

	private static void simulate(int cur, int cnt) {
		if(map[cur] < cnt) return;
		
		map[cur] = cnt;
		
		for(int i=1; i <= 6; i++) {
			if(cur + i > 100) break; //주사위를 굴렸을 때 100이 넘어가면 끝
			if(ladderMap[cur + i] > 0) simulate(ladderMap[cur + i], cnt + 1);
			else if(snakeMap[cur + i] > 0) simulate(snakeMap[cur + i], cnt + 1);
			else simulate(cur + i, cnt + 1);
		}		
	}
	
}