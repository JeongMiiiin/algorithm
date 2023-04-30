import java.util.PriorityQueue;

/*
 * 프로그래머스 셔틀버스
 * 
 * 셔틀은 09:00부터 총 n회, t분 간격으로 역에 도착하며, 하나의 셔틀에는 최대 m명의 승객이 탈 수 있다.
 * 도착했을 때, 도착한 순간에 대기열에 선 크루까지 포함해서 대기 순서대로 태우고 바로 출발
 * 
 * 콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각을 구하여라.
 * 
 *  
 * 
*/

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        //9시 표시
		int start = 540;
		
		PriorityQueue<Integer> crew = new PriorityQueue<>();
		for(String s : timetable) crew.add(changeTimeFromStringToInt(s));
		
		String answer = "";
		
		for(int i=0; i < n; i++) {
			int shuttle = start + (i * t); //셔틀버스 시간
			if(shuttle < crew.peek() || crew.size() < m) { //가장 먼저 오는 크루의 시간보다 현재 셔틀버스 시간이 더 빠를 때
				answer = changeTimeFromIntToString(shuttle);
				continue;
			} else { //현재 셔틀버스의 시간보다 같거나 빠를 때
				int minCnt = 1;
				int minTime = crew.poll();
				int cnt = 1;
				while(cnt < m && !crew.isEmpty()) {
					if(shuttle < crew.peek()) break;
					else {
						int nextCrew = crew.poll();
						if(nextCrew == minTime) minCnt++;
						else {
							minTime = nextCrew;
							minCnt = 1;
						}
						
						cnt++;
					}
				}
				
				if(minCnt == m) answer = changeTimeFromIntToString(minTime - 1);
				else if(cnt == m) answer = changeTimeFromIntToString(minTime - 1);
				else answer = changeTimeFromIntToString(minTime);
			}
			
		}
		
        return answer;
        
    }
	
	//시간 String을 int로 변환해주는 함수
	private static int changeTimeFromStringToInt(String s) {
		String[] time = s.split(":");
		int result = 0;
		
		result += (Integer.parseInt(time[0]) * 60); //시 계산
		result += Integer.parseInt(time[1]); //분 계산
		
		return result;
	}
	
	//시간 int를 String으로 변환해주는 함수
	private static String changeTimeFromIntToString(int n) {
		StringBuffer result = new StringBuffer();
		
		int hour = n / 60;
		int min = n % 60;
		
		if(hour < 10) result.append("0" + hour);
		else result.append(hour);
		
		result.append(":");
		
		if(min < 10) result.append("0" + min);
		else result.append(min);
			
		return result.toString();
	}
}