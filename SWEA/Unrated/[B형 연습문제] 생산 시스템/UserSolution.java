package practice_b_2_2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * SWEA B형 연습문제 - 생산 시스템
 * 
 * L개의 생산라인이 있다. 그리고 모든 생산 라인에서 공유해서 사용하는 M개의 서로 다른 장비가 있다.
 * 각 생산 라인 별로 생산 요청이 들어오는데, 생산될 제품의 ID와 생산에 필요한 장비,
 * 그리고 생산에 필요한 시간이 주어진다.
 * 
 * 각 생산 라인은 요청이 들어온 순서대로 생산을 한다.
 * 각 장비를 동시에 사용될 수 없기 때문에, 필요한 장비가 다른 생산 라인에서 사용 중이라면 대기해야 한다.
 * 장비의 사용이 끝난 시점에, 해당 장비를 필요로 하는 생산라인이 여러개라면 생산 라인 번호가 작은 순서대로
 * 장비가 할당됨.
 * 
 * 동작하지 않는 생산 라인에 생산 요청(필요한 장비 E, 필요한 시간 T)이 X 시각에 추가된 경우,
 * 1. 대기 중인 생산 요청이 있다면, 대기열의 마지막에 추가한다.
 * 2. 대기 중인 생산 요청이 없고, 장비 E가 사용 가능하다면 생산 라인은 X 시각에 생산을 시작한다.
 * 3. 대기 중인 생산 요청이 없지만 장비 E를 다른 생산 라인에서 사용 중인 경우, 생산 라인은 동작하지 않고 대기하게 됨.
 * X시각에 생산을 시작한 생산 라인은 X + T 시각에 생산을 종료.
 * X + T 시각에 대기 중인 요청이 있다면 X + T 시각에 다시 생산을 시도 (이때 X + T 시각에 들어온 요청도 포함)
 * 
 * 만들어야할 것
 * 1. 생산라인 (번호, 사용중, 사용중인 장비 번호, 생산중인 제품 번호, 사용 시작 시간, 사용 완료 시간, 대기열 )
 * 2. 장비 (번호, 사용중)
 * 3. 제품 (번호, 만들고 있는지 여부, 완료 여부)
*/

class UserSolution {
	static class ProductLine {
		int lineId, useEId, curPid, startTime, completeTime;
		boolean useStatus;
		Queue<Product> waits;
		public ProductLine(int lineId) {
			this.lineId = lineId;
			this.curPid = -1;
			this.startTime = -1;
			this.completeTime = -1;
			this.useStatus = false;
			this.waits = new LinkedList<>();
		}
	}
	static class Equipment {
		int eId;
		boolean useStatus;
		public Equipment(int eId) {
			this.eId = eId;
			this.useStatus = false;
		}
	}
	static class Product {
		int pId, needEid, needTime;
		boolean progress, complete;
		public Product(int pId, int needTime, int needEid) {
			this.pId = pId;
			this.needTime = needTime;
			this.needEid = needEid;
			this.progress = false;
			this.complete = false;
		}
	}
	
	static int lineSize, equsSize;
	static ProductLine[] lines = new ProductLine[500];
	static Equipment[] equs = new Equipment[500];
	static Map<Integer, Product> products = new HashMap<>();
	static int curTime;
	
	void init(int L, int M) {
		//초기화
		Arrays.fill(lines, null);
		Arrays.fill(equs, null);
		products.clear();
		curTime = 0;
		
		//생산라인 배치
		lineSize = L;
		for(int i=0; i < L; i++) lines[i] = new ProductLine(i);
		
		//장비 배치
		equsSize = M;
		for(int i=0; i < M; i++) equs[i] = new Equipment(i);
		
		return;
	}

	int request(int tStamp, int pId, int mLine, int eId, int mTime) {
		int prevTime = tStamp - curTime;
		
		curTime = tStamp;
		
		int idx = 0;

		//생산라인 가동 (현재 전 시간까지
		while(idx++ < prevTime - 1) checkLines();
		
		Product p = new Product(pId, mTime, eId);
		//제품에 추가
		products.put(pId, p);
		
		lines[mLine].waits.add(p);
		
		checkLines();
		
		//사용중이 아닐 때
		if(lines[mLine].useStatus) return lines[mLine].curPid; 
		else return -1;
		
	}
	
	private void startProduct(int mLine, Product p) {
		ProductLine cur = lines[mLine];
		//장비 사용 체크
		equs[p.needEid].useStatus = true;
		//생산라인 사용 체크
		cur.curPid = p.pId;
		cur.useEId = p.needEid; 
		cur.startTime = 0;
		cur.completeTime = p.needTime;
		cur.useStatus = true;
		//제품 생산 시작 표시
		products.get(p.pId).progress = true;
	}

	private void checkLines() {
		ProductLine cur;
		for(int i=0; i < lineSize; i++) {
			cur = lines[i];
			if(cur.useStatus) { //현재 생산중인 항목이 있을 때
				cur.startTime++; //생산중 올려주기
				if(cur.startTime == cur.completeTime) { //생산이 완료되었을 때
					cur.useStatus = false; //생산라인 사용안함 처리
					equs[cur.useEId].useStatus = false; //장비 사용안함 처리
					Product completeP = products.get(cur.curPid);
					completeP.complete = true;
					cur.curPid = -1;
				}
			}
		}
		
		for(int i=0; i < lineSize; i++) {
			cur = lines[i];
			//생산을 시작할 수 있고, 대기중인 제품이 있을 떄
			if(!cur.useStatus && cur.waits.size() > 0) {
				Product p = cur.waits.peek();
				if( !equs[p.needEid].useStatus ) { //장비 사용이 가능할 때
					startProduct(i, p);
					cur.waits.poll();
				}
			}
		}
		
	}

	int status(int tStamp, int pId) {
		int prevTime = tStamp - curTime;
		
		curTime = tStamp;
		
		int idx = 0;

		//생산라인 가동
		while(idx++ < prevTime) checkLines();
		
		//요청된 제품인 경우
		if( products.get(pId) != null ) {
			if(products.get(pId).complete) return 3; //완료상태인 경우
			if(products.get(pId).progress) return 2; //진행상태인 경우
			return 1; //대기상태인 경우
		}
		
		return 0; //요청된 적이 없는 제품인 경우
	}
}