import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 17140 - 이차원 배열과 연산
 * 
 * 3 x 3 배열 A
 * 배열의 인덱스는 1부터 시작
 * 1초가 지날때마다 배열에 연산 적용
 * R : 배열 A의 모든 행에 대해서 정렬 수행
 * C : 배열 A의 모든 열에 대해서 정렬 수행
 * 행의 개수 >= 열의 개수 -> R연산
 * 행의 개수 < 열의 개수   -> C연산
 * 
 * 한 행 또는 열에 있는 수를 정렬하려면, 각각의 수가 몇 번 나왔는지 알아야 한다.
 * 그 다음, 수의 등장 횟수가 커지는 순으로,
 * 그러한 것이 여러가지면 수가 커지는 순으로 정렬
 * 정렬된 결과를 배열에 넣을 때는, 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저
 * 
 * 정렬된 결과를 배열에 다시 넣으면 행 또는 열의 크기가 가장 큰 길이에 맞춰 변한다
 * 빈 공간에는 0으로 채우기 (정렬 시, 0은 무시)
 * 행 또는 열의 크기가 100을 넘어가는 경우에 처음 100개를 제외한 나머지는 버린다.
 * 
 * A[r][c] == k가 되기 위한 최소 시간 출력
 * 단, 100초가 지나도 k가 되지 않을 시에는 -1 출력
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Map<Integer, Integer> map = new HashMap<>();
		
		//초기값 세팅
		for(int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j <= 3; j++) map.put((101 * i) + j, Integer.parseInt(st.nextToken()));
		}
		
		int ans = -1, rowCnt = 3, colCnt = 3;
		
		while(ans++ < 101) {
			
			//해당 위치 값이 k와 동일할 때
			if(map.get((101 * r) + c) != null && map.get((101 * r) + c) == k) break;
			
			if(rowCnt >= colCnt) colCnt = calcR(map, rowCnt, colCnt);
			else rowCnt = calcC(map, rowCnt, colCnt);
		}
		
		//100초를 넘어갈 때
		if(ans >= 101) ans = -1;
		
		System.out.println(ans);
	}

	static class Num implements Comparable<Num>{
		int key, value;
		
		public Num(int key, int value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(Num o) {
			return this.value - o.value != 0 ? this.value - o.value : this.key - o.key;
		}
	}

	//R연산
	private static int calcR(Map<Integer, Integer> map, int rowCnt, int colCnt) {
		Map<Integer, Num> temp = new HashMap<>();
		PriorityQueue<Num> temps = new PriorityQueue<>();
		int[] hasCol = new int[rowCnt + 1];
		int tempCnt = colCnt;
		for(int i=1; i <= rowCnt; i++) {
			temp.clear();
			temps.clear();
			for(int j=1; j <= tempCnt; j++) {
				int target = map.get((101 * i) + j);
				if(target == 0) continue;
				if(temp.get(target) != null) temp.get(target).value++;
				else temp.put(target, new Num(target, 1));
			}
			
			for(Entry<Integer, Num> pair : temp.entrySet()) temps.add(pair.getValue()); 
			
			int idx = 0;
			Num cur;
			while( !temps.isEmpty() && idx < 101) {
				cur = temps.poll();
				map.put((101 * i) + (++idx), cur.key);
				map.put((101 * i) + (++idx), cur.value);
			}
			
			hasCol[i] = idx;
			colCnt = Math.max(idx, colCnt); 
		}
		
		//빈 곳 0으로 채우기
		for(int i=1; i <= rowCnt; i++) for(int j=hasCol[i] + 1; j <= colCnt; j++) map.put((101* i) + j, 0);
		
		return colCnt;
	}
	
	//C연산
	private static int calcC(Map<Integer, Integer> map, int rowCnt, int colCnt) {
		Map<Integer, Num> temp = new HashMap<>();
		PriorityQueue<Num> temps = new PriorityQueue<>();
		int[] hasRow = new int[colCnt + 1];
		int tempCnt = rowCnt;
		for(int i=1; i <= colCnt; i++) {
			temp.clear();
			temps.clear();
			for(int j=1; j <= tempCnt; j++) {
				int target = map.get((101 * j) + i);
				if(target == 0) continue;
				if(temp.get(target) != null) temp.get(target).value++;
				else temp.put(target, new Num(target, 1));
			}
			
			for(Entry<Integer, Num> pair : temp.entrySet()) temps.add(pair.getValue()); 
			
			int idx = 0;
			Num cur;
			while( !temps.isEmpty() && idx < 101) {
				cur = temps.poll();
				map.put((101 * (++idx)) + i, cur.key);
				map.put((101 * (++idx)) + i, cur.value);
			}
			
			hasRow[i] = idx;
			rowCnt = Math.max(idx, rowCnt); 
		}
		
		//빈 곳 0으로 채우기
		for(int i=1; i <= colCnt; i++) for(int j=hasRow[i] + 1; j <= rowCnt; j++) map.put((101* j) + i, 0);
		
		return rowCnt;
	}
}