import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * SWEA 2383 - 점심 식사시간
 * 주어지는 값
 * N : 방 크기
 * 두번째 줄부터 N번째 줄 : 방의 위치상태
 * 계단은 무조건 두개가 있음
 * N x N 크기의 정사각형 모양의 방에 사람들이 있음
 * 점심을 위해 최대한 빠른 시간 내에 내려가야 함
 * 방 안의 사람들은 P로, 계단 입구를 S라고 한다
 * 이동완료시간 -> 모든 사람들이 계단을 내려가 아래층으로 이동을 완료한 시간
 * 계단 입구까지의 이동시간과 계단을 내려가는 시간이 모두 포함
 * 계단 입구까지 이동 시간 -> |PR - SR| + |PC - SC|
 * 계단을 내려가는 시간 -> K분 후 아래칸으로 내려감 단, 계단은 최대 3명 이용 가능, 이미 3명이 있을 경우 대기해야 함
 * 
 * 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우 출력
 * 
 * 1.모든 사람들은 두가지의 선택지가 있음
 * 1-1. 첫번째 계단을 선택하는 경우와 두번째 계단을 선택하는 경우
 * 1-2. 순열로 그 선택지를 만든다?
 * 1-3. 1 1 1 1 1
 * 
*/
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static class Person {
		int no, r, c, usingSquareNo, square1Arrive, square2Arrive;
		boolean square = false, complete = false;
		public Person(int no, int r, int c, int square1Arrive, int square2Arrive) {
			this.no = no;
			this.r = r;
			this.c = c;
			this.square1Arrive = square1Arrive;
			this.square2Arrive = square2Arrive;
		}
		
	}
	static class Square {
		int r, c, usingNo1 = -1, usingNo2 = -1, usingNo3 = -1, usingTime, no1Time = 0, no2Time = 0, no3Time = 0;

		public Square(int r, int c, int usingTime) {
			super();
			this.r = r;
			this.c = c;
			this.usingTime = usingTime;
		}
		
	}
	
	static int N, P, ans;
	static int[][] map;
	static Square[] squares;
	static int[] selected;
	static List<Person> persons;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			//초기화
			squares = new Square[2];
			ans = Integer.MAX_VALUE;
			persons = new ArrayList<>();
			
			//입력
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int squareIdx = 0;
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					//계단정보 담기
					if(map[i][j] > 1) squares[squareIdx++] = new Square(i, j, map[i][j]);
				}
			}
			
			int personIdx = 0;
			//사람들 리스트 만들기
			for(int i=0; i < N; i++) {
				for(int j=0; j < N; j++) {
					if(map[i][j] != 1) continue; //빈 곳이거나 계단인 경우 패스
					int arrive1 = Math.abs(i - squares[0].r) + Math.abs(j - squares[0].c);
					int arrive2 = Math.abs(i - squares[1].r) + Math.abs(j - squares[1].c);
					persons.add(new Person(personIdx++, i, j, arrive1, arrive2));
				}
			}
			
			P = persons.size();
			
			selected = new int[P];
			Arrays.fill(selected, 1);
			
			subset();
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.close();
	}

	private static void subset() {
		for(int i=0; i < 1 << P; i++) {
			for(int j=0; j < P; j++) {
				if((i & 1 << j) != 0) selected[j] = 2;
				else selected[j] = 1;
			}
			settingSimulate();
			simulate(1);
		}
		
	}

	//양쪽으로 보내고 계산하기
	private static void simulate(int time) {
		if(ans <= time) {
			setOrigin();
			return;
		}
		
		//현재 계단상태 점검
		checkSquare();
		
		//해당 경로대로 완료 후에도 time이 현재보다 작은 경우에는 값 바꿔주기
		if(complateAll()) {
			ans = time + 1;
			setOrigin();
			return;
		}
		
		List<Person> squareWaiting = new ArrayList<>();
		for(int i=0; i < P; i++) {
			Person p = persons.get(i);
			if(p.square1Arrive <= time && p.usingSquareNo == 1 && !p.square) squareWaiting.add(p);
		}
		
		//지정
		selectSquare(squareWaiting, 0);
		
		squareWaiting = new ArrayList<>();
		for(int i=0; i < P; i++) {
			Person p = persons.get(i);
			if(p.square2Arrive <= time && p.usingSquareNo == 2 && !p.square) squareWaiting.add(p);
		}
		
		//지정
		selectSquare(squareWaiting, 1);
		
		//현재 안 끝났을 때 1분 후 시뮬레이션 실행
		simulate(time + 1);
	}

	private static void selectSquare(List<Person> squareWaiting, int num) {
		//첫번째 이용고객이 비어있을 때
		if(squares[num].usingNo1 == -1 && squareWaiting.size() > 0) {
			Person p = squareWaiting.get(0);
			squares[num].usingNo1 = p.no;
			p.square = true;
			squareWaiting.remove(0);
		}
		
		//두번째 이용고객이 비어있을 때
		if(squares[num].usingNo2 == -1 && squareWaiting.size() > 0) {
			Person p = squareWaiting.get(0);
			squares[num].usingNo2 = p.no;
			p.square = true;
			squareWaiting.remove(0);
		}
		
		//두번째 이용고객이 비어있을 때
		if(squares[num].usingNo3 == -1 && squareWaiting.size() > 0) {
			Person p = squareWaiting.get(0);
			squares[num].usingNo3 = p.no;
			p.square = true;
			squareWaiting.remove(0);
		}
	}

	private static void checkSquare() {
		for(int i=0; i < 2; i++) {
			int completeTime = squares[i].usingTime;
			if(squares[i].usingNo1 > -1) {
				squares[i].no1Time++;
				if(squares[i].no1Time == completeTime) {
					persons.get(squares[i].usingNo1).complete = true;
					squares[i].usingNo1 = -1;
					squares[i].no1Time = 0;
				}
			}

			if(squares[i].usingNo2 > -1) {
				squares[i].no2Time++;
				if(squares[i].no2Time == completeTime) {
					persons.get(squares[i].usingNo2).complete = true;
					squares[i].usingNo2 = -1;
					squares[i].no2Time = 0;
				}
			}
			
			if(squares[i].usingNo3 > -1) {
				squares[i].no3Time++;
				if(squares[i].no3Time == completeTime) {
					persons.get(squares[i].usingNo3).complete = true;
					squares[i].usingNo3 = -1;
					squares[i].no3Time = 0;
				}
			}
		}
		
	}

	private static void settingSimulate() {
		for(int i=0; i < P; i++) persons.get(i).usingSquareNo = selected[i];
	}
	
	//원상태로 복구
	private static void setOrigin() {
		for(int i=0; i < P; i++) {
			persons.get(i).square = false;
			persons.get(i).complete = false;
		}
		for(int i=0; i < 2; i++) {
			squares[i].usingNo1 = -1;
			squares[i].usingNo2 = -1;
			squares[i].usingNo3 = -1;
			squares[i].no1Time = 0;
			squares[i].no2Time = 0;
			squares[i].no3Time = 0;
		}
	}

	private static boolean complateAll() {
		boolean flag = true;
		for(int i=0; i < P; i++) {
			if( !persons.get(i).complete ) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
