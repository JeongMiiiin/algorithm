package practice_b_3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * 바이러스 감염자의 동선을 추적하는 프로그램을 개발
 * 
 * 10000 x 10000 의 구분된 지도 이용
 * 지도 상에는 방문 가능한 장소가 추가되거나 삭제될 수 있다.
 * 
 * 이동 경로는 처음 이동을 시작한 장소와 이후 움직인 방향의 목록으로 주어짐.
 * 감염자는 원래 있던 장소에서 출발하여 주어지는 이동 방향을 향해 가장 먼저 만나는 방문 가능한 장소까지 이동.
 * 이동은 주어진 움직인 방향의 개수만큼 반복
 * 감염자의 동선이 확정된 이후, 감염자가 방문한 장소들은 소독이 되기 전까지 방문이 금지.
 * 
 * 감염자가 방문했던 장소들의 소독이 완료되면, 그 이후부터는 다시 방문 가능한 장소가 됨.
 * 
 * 필요한 객체 : 장소, 감염자
 * 필요한 배열 : 지도, 장소 맵
 * 
 * 1. 지도와 장소 맵을 그림.
 * 2. 지도는 0, 1, 2로 표시 (0 : 비어있음, 1 : 방문 가능한 장소, 2 : 방문 불가능한 장소)
 * 
*/

class UserSolution {
	
	static Map<Integer, Integer> map = new HashMap<>();
	static int[][] placeList = new int[50001][2];
	static int[][] infectorList = new int[1001][100];
	static boolean[] infector = new boolean[1001];
	void init() {
		//맵 초기화
		map.clear();
		settingInfector();
	}
	
	void settingInfector() {
		int idx = 0;
		while(infector[++idx]) {
			Arrays.fill(infectorList[idx], 0);
			infector[idx] = false;
		}
	}
	
	/*
	 * r행 c열에 방문 가능한 장소 pID를 추가한다.
	 * pID는 1부터 시작하여 1씩 증가
	*/
	void addPlace(int pID, int r, int c) {
		placeList[pID][0] = r;
		placeList[pID][1] = c;
		map.put(10000 * r + c, pID);
	}

	/*
	 * pID 장소를 삭제
	*/
	void removePlace(int pID) {
		map.remove(10000 * placeList[pID][0] + placeList[pID][1]);
	}
	
	/*
	 * 감염자 uID가 이동한 동선이 주어짐.
	 * 각 테케에서 contactTracing() 함수로 불리는 uID는 1부터 시작하여 1씩 증가
	 * moveInfo[0] -> 처음 방문한 장소의 pID
	 * moveInfo[1] ~ moveInfo[visitNum - 1] -> 감염자가 이동한 방향 0 ~ 7
	 * 시작점을 포함해 감염자가 방문한 모든 장소의 pID들을 방문 순서대로 visitList[] 배열에 저장하여 반환
	 * 같은 장소를 여러 번 방문했을 수도 있다.
	 * 
	 * 함수 호출이 끝나면 방문이 불가능함.
	*/
	
	void contactTracing(int uID, int visitNum, int moveInfo[], int visitList[]) {
		//상 우상 우 우하 하 좌하 좌 좌상
		int[] dr1 = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dc1 = {0, 1, 1, 1, 0, -1, -1, -1};
		//감염자 생성 및 
		int initpID = moveInfo[0];
		int visitIdx = 0;
		visitList[visitIdx++] = initpID;
		int[] p = placeList[initpID];
		
		infector[uID] = true;
		
		//방문하기
		for(int i=1; i < visitNum; i++) {
			int r = p[0];
			int c = p[1];
			int d = moveInfo[i];
			while(true) {
				r += dr1[d];
				c += dc1[d];
				//방문 가능한 장소인 경우
				if(map.get(10000 * r + c) != null && map.get(10000 * r + c) > 0) break;
			}
			int visit = map.get(10000 * r + c);
			visitList[visitIdx++] = visit;
			p = placeList[visit];
		}
		
		for(int i=0; i < visitNum; i++) infectPlace(uID, visitList[i], i);
	}
	
	//감염장소로 만들기
	void infectPlace(int uID, int pID, int idx) {
		int[] p = placeList[pID];
		infectorList[uID][idx] = pID;
		map.put(10000 * p[0] + p[1], -1);
	}
	
	/*
	 * uID 확진자가 방문했던 모든 장소의 소독이 완료되어 다시 이동이 가능하게 됨
	*/
	void disinfectPlaces(int uID) {
		int[] u = infectorList[uID];
		for(int n : u) {
			if(n == 0) break;
			int[] p = placeList[n];
			if(map.get(10000 * p[0] + p[1]) != null && map.get(10000 * p[0] + p[1]) == -1) {
				map.put(10000 * p[0] + p[1], n);
			}
		}
	}
}