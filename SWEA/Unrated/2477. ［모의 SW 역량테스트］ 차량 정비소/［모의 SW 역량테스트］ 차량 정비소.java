import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 2477 - 차량정비소
 * 주어지는 값
 * T : 테스트 케이스 수
 * N : 접수 창구의 개수
 * M : 정비 창구의 개수
 * K : 방문한 고객 수
 * A : 지갑을 분실한 고객이 이용한 접수 창구 번호
 * B : 지갑을 분실한 고객이 이용한 정비 창구 번호
 * 두번째 줄 : 접수창구번호들의 소요시간
 * 세번째 줄 : 정비창구번호들의 소요시간
 * 네번째 줄 : 고객이 차량 정비소를 방문하는 시간
 * 
 * 지갑을 잃어버린 고객을 찾아야 한다
 * 고객만족도 설문지에는 고객이 이용했던 접수 창구번호와 정비 창구번호가 존재
 * 정비소에는 N개의 접수창구와 M개의 정비 창구가 있음
 * 접수창구 i에서 고객 한 명의 고장을 접수하는 데 걸리는 처리 시간은 ai이다
 * 정비창구 j에서 고객 한 명의 차량을 정비하는 데 걸리는 처리 시간은 bj이다
 * 지금까지 차량 정비소를 방문한 고객은 K명
 * 고객번호 k의 고객이 차량 정비소에 도착하는 시간은 tk
 * 고객이 차량정비소에 도착했을 때, 빈 접수 창구가 있는 경우 빈 접수 창구에 가서 고장 접수
 * 없으면 기다림
 * 접수 창구의 우선순위
 * 고객번호가 낮은 순, 창구번호가 작은 순
 * 정비 창구의 우선순위
 * 먼저 도착한 고객 우선순위
 * 두 명 이상의 고객이 한번에 올 경우 창구번호가 작은 순
 * 정비창구번호가 작은 순
 * 
 * 고객의 도착 시간 tk, 접수창구의 처리시간 ai, 정비창구의 처리시간 bj가 주어졌을 때
 * 지갑을 분실한 고객과 같은 접수 창구와 같은 정비 창구를 이용한 고객의 고객 번호들을 찾아 그 합을 출력하는 프로그램 작성
*/
public class Solution {
	static class Customer implements Comparable<Customer>{
		int no, receiptNo = -1, fixNo = -1, arriveTime, receiptCompleteTime = 0;
		boolean receiptStart = false, receiptComplete = false, fixStart = false, complete = false;

		public Customer(int no, int arriveTime) {
			this.no = no;
			this.arriveTime = arriveTime;
		}

		@Override
		public int compareTo(Customer o) {
			if(this.receiptCompleteTime != o.receiptCompleteTime) return (this.receiptCompleteTime - o.receiptCompleteTime);
			else if(this.receiptNo != o.receiptNo) return (this.receiptNo - o.receiptNo);
			else return (this.no - o.no);
		}
	}
	static class Window {
		int no, completeTime, usingTime = 1, usingCustomNo = -1;
		boolean using = false;
		public Window(int no, int completeTime) {
			this.no = no;
			this.completeTime = completeTime;
		}
		
	}
	
	static int N, M, K, A, B;
	static Customer[] customers;
	static Window[] receiptWindows, fixWindows;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			receiptWindows = new Window[N];
			fixWindows = new Window[M];
			
			//접수창구 생성
			st = new StringTokenizer(br.readLine());
			for(int i=0; i < N; i++) receiptWindows[i] = new Window(i + 1, Integer.parseInt(st.nextToken()));
			//정비창구 생성			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i < M; i++) fixWindows[i] = new Window(i + 1, Integer.parseInt(st.nextToken()));
			
			//고객생성
			customers = new Customer[K];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i < K; i++) customers[i] = new Customer(i + 1, Integer.parseInt(st.nextToken()));
			
			//시뮬레이션 실행
			simulate(0);
			
			int ans = 0;
			for(int i=0; i < K; i++) if(customers[i].receiptNo == A && customers[i].fixNo == B) ans += customers[i].no;
			if(ans == 0) ans--;
			
			//출력
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.close();
	}
	private static void simulate(int time) {
		boolean allComplete = true;
		for(int i=0; i < K; i++) {
			if( !customers[i].complete ) {
				allComplete = false;
				break;
			}
		}  
		
		if(allComplete) return; //모두가 통과했을 때는 시뮬레이션 끝!
		Queue<Customer> waiting = new PriorityQueue<>();
		
		//접수창구 점검
		List<Window> currentPossibleWindow = new ArrayList<>();
		checkWindow(currentPossibleWindow, 1, time);
		
		//도착을 하였으나 아직 접수하지 못한 경우에 접수창구 웨이팅 리스트에 담기

		for(int i=0; i < K; i++) if(customers[i].arriveTime <= time && !customers[i].receiptStart) waiting.offer(customers[i]);

		//접수창구에 배치하기
		selectWindow(waiting, currentPossibleWindow, 1, time);

		waiting.clear();
		//정비창구 점검
		currentPossibleWindow = new ArrayList<>();
		checkWindow(currentPossibleWindow, 2, time);
		
		for(int i=0; i < K; i++) if(customers[i].receiptComplete && !customers[i].fixStart) waiting.offer(customers[i]);
		
		//정비창구에 배치하기
		selectWindow(waiting, currentPossibleWindow, 2, time);
		
		simulate(time + 1);
	}
	
	private static void selectWindow(Queue<Customer> waiting, List<Window> currentWindow, int category, int time) {
		// TODO Auto-generated method stub
		while( !waiting.isEmpty() && currentWindow.size() > 0) {
			Customer c = waiting.poll();
			Window w = currentWindow.get(0);
			if(category == 1) {
				c.receiptNo = w.no; 
				c.receiptStart = true;
			} else {
				c.fixNo = w.no;
				c.fixStart = true;
			}
			w.usingCustomNo = c.no;
			w.using = true;
			currentWindow.remove(0);
		}
	}
	private static void checkWindow(List<Window> possibleList, int category, int time) {
		int max = category == 1 ? N : M;
		Window[] targetWindow = category == 1 ? receiptWindows : fixWindows;
		
		for(int i=0; i < max; i++) {
			Window w = targetWindow[i];
			if( !w.using ) possibleList.add(w);
			else if (w.completeTime == w.usingTime) {
				w.using = false;
				w.usingTime = 1;
				if(category == 1) {
					customers[w.usingCustomNo - 1].receiptNo = w.no;
					customers[w.usingCustomNo - 1].receiptComplete = true;
					customers[w.usingCustomNo - 1].receiptCompleteTime = time;
				} else customers[w.usingCustomNo - 1].complete = true;
				possibleList.add(w);
			} else w.usingTime++;
		}
	}
}
