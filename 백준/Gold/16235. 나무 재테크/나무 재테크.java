import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 16235 - 나무 재테크
 * 
 * N x N 크기의 땅
 * 각각의 칸은 (r,c)로 나타내며
 * r은 가장 위에서부터 떨어진 칸의 개수
 * c는 가장 왼쪽으로부터 떨어진 칸의 개수
 * r과 c는 1부터 시작
 * 가장 처음 양분은 모든 칸에 5만큼 배분
 * M개의 나무를 구매해 땅에 심기
 * 같은 칸에 여러개의 나무 심기 가능
 * 나무는 사계절을 보내면서 아래와 같은 과정 반복
 * 1. 자신의 나이만큼 양분을 먹고, 나이가 1 증가
 * 2. 하나의 칸에 여러 개의 나무가 있다면 나이가 어린 나무부터 양분을 먹음. (단, 양분이 부족하여 자신의 나이만큼 양분을 먹을 수 없는 경우 사망)
 * 3. 여름에는 봄에 죽은 나무가 양분으로 변하게 됨.
 * 3-1. 각각의 죽은 나무마다 나이를 2로 나눈 값이 해당 칸에 추가됨.
 * 4. 가을에는 나무가 번식
 * 4-1. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생김. (단, 상도의 땅에 벗어나는 칸에는 나무가 생기지 않음)
 * 5. 겨울에는 로봇이 돌아다니면서 땅에 양분을 추가
 * 
 * K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하라
 * 
 * 시뮬레이션 문제
 * 1. 나무 클래스 만들기 (O)
 * 2. 맵 세팅하기 (O)
 * 3. 나무들 세팅하기 (PQ로 구현) (O)
 * 4. 나무들 양분 먹기 (봄)
 * 5. 죽은 나무들 골라내서 양분으로 맵에 세팅해주기 (여름)
 * 6. 번식할 나무가 있으면 번식하기 (가을)
 * 7. 뿌려줘야 하는 양분 맵에 세팅해주기 (겨울)
 * 
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Tree implements Comparable<Tree>{
		int r, c, age;
		boolean dead = false;
		public Tree(int r, int c, int age){
			this.r = r;
			this.c = c;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	
	
	
	static int N, M, K;
	static int[][] map, resources;
	static Queue<Tree> treeList = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		resources = new int[N + 1][N + 1];
		
		//resource 세팅
		for(int i=1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j <= N; j++) resources[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//초기 5로 전부 세팅
		for(int i=1; i <= N; i++) Arrays.fill(map[i], 5);
		for(int i=0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			treeList.offer(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		simulate(0);
		
		System.out.println(treeList.size());
	}
	
	//좌상 상 우상 우 우하 하 좌하 좌
	static int[] dr1 = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc1 = {-1, 0, 1, 1, 1, 0, -1, -1};
	private static void simulate(int year) {
		if(year == K) return; //목표년도에 도달하면 끝
		
		//봄 : 나무들을 키운다.
		int treeSize = treeList.size();
		Tree[] tempList = new Tree[treeSize];
		Tree t;
		for(int i=0; i < treeSize; i++) {
			t = treeList.poll();
			//양분을 줄만한 땅에 위치할 때
			if(map[t.r][t.c] >= t.age) {
				map[t.r][t.c] -= t.age;
				t.age++;
			} else t.dead = true;
			tempList[i] = t;
		}
		
		//여름 : 죽은 나무가 있으면 걸러낸다.
		for(int i=0; i < treeSize; i++) {
			t = tempList[i];
			if(t.dead) { //죽은 나무이면 양분으로 변하기
				int food = t.age / 2;
				map[t.r][t.c] += food;
			}
		}
		
		//가을 : 번식을 한다.
		for(int i=0; i < treeSize; i++) {
			t = tempList[i];
			if(t.dead) continue; //죽은 나무면 패스
			treeList.offer(t);
			if(t.age % 5 == 0) { //5의 배수일 때
				int nr, nc;
				for(int d=0; d < 8; d++) {
					nr = t.r + dr1[d];
					nc = t.c + dc1[d];
					if(!outMap(nr, nc)) treeList.offer(new Tree(nr, nc, 1));
				}
			}
		}
		
		//겨울 : 양분을 준다
		for(int i=1; i <= N; i++) for(int j=1; j <= N; j++) map[i][j] += resources[i][j];
		
		simulate(year + 1);
	}
	
	private static boolean outMap(int r, int c) {
		return (r < 1 || r > N || c < 1 || c > N);
	}
}