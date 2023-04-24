import java.util.HashMap;
import java.util.Map;
/*
 * 프로그래머스 보석쇼핑
 * 
 * 특점 범위의 물건들을 모두 구매함.
 * 특정 범위의 보석을 모두 구매하되 조건이 있음.
 * -> 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매
 * 
 * 담고있지 않은 보석이 나오면 범위 늘려주는데,
 * 기존 끝 범위에서부터 새로운 보석이 나오는 범위까지
 * start와 비교하면서제거가 가능하면 제거해주기
*/

class Solution {
    static Map<String, Integer> collectionGem = new HashMap<>();
    
    public int[] solution(String[] gems) {
        collectionGem.clear();
		
		for(String gem : gems) collectionGem.put(gem, collectionGem.getOrDefault(gem, 0) + 1);
		
		int totalCnt = collectionGem.size();
		
		//하나만 존재할 때는 제일 처음 돌려주기
		if(totalCnt == 1) return new int[]{1,1};
		
		//탐색을 위해 초기화
		collectionGem.clear();
		
		//최소길이를 업데이트할 변수
		int minLen = Integer.MAX_VALUE, minStart = 0, minEnd = 0;
		//포인터 시작점 끝점
		int start = 0, end = 0;
		//시작점 값 넣어놓기
		collectionGem.put(gems[0], 1);
		
		int curLen;
		
		//두번째부터 찾기
		for(int i=1; i < gems.length; i++) {			
			//컬렉션이 완성되었을 경우
			if(collectionGem.size() == totalCnt) { 
				curLen = end - start;
				if(curLen < minLen) {
					minLen = curLen;
					minStart = start;
					minEnd = end;
				}	
			}
			
			collectionGem.put(gems[++end], collectionGem.getOrDefault(gems[end], 0) + 1);
			
			while(collectionGem.get(gems[start]) > 1) collectionGem.put(gems[start], collectionGem.get(gems[start++]) - 1);
			
		}
		
		curLen = end - start;
		if(curLen < minLen) {
			minStart = start;
			minEnd = end;
		}
		
		if(minEnd == 0) minEnd = gems.length - 1;
		
        int[] answer = {minStart,minEnd};
        
        //순서로 처리해줘야 해서 1 더하기
        answer[0]++;
        answer[1]++;
        return answer;
    }
}