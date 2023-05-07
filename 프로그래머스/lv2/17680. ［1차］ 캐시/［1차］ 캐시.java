import java.util.LinkedList;
import java.util.Queue;

/*
 * 프로그래머스 - 캐시
 * 
 * 주어지는 값
 * 캐시 크기, 도시이름 배열
 * 
 * 도시 이름을 검색하면 해당 도시와 관련된 맛집 게시물들을 DB에서 읽어 보여주는 서비스 개발
 * DB에서 게시물을 가져오는 부분의 실행시간이 너무 오래걸림.
 * 
 * cache hit인 경우 실행시간 1
 * cache miss인 경우 실행시간 5
 * 
 * DB 캐시를 적용할 때 캐시 크기에 따른 실시간 측정 프로그램 작성
*/
class Solution {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> cache = new LinkedList<>();
		
		int answer = 0;
		
		for(String s : cities) {
			String target = s.toLowerCase();
			answer++;
			if(cache.contains(target)) cache.remove(target);
			else answer += 4;
			
			if(cache.size() < cacheSize) cache.add(target);
			else {
				if(cache.size() > 0) cache.poll();
				if(cacheSize > 0) cache.add(s.toLowerCase());
			}
		}
		
        return answer;
    }
}