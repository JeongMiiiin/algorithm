import java.util.ArrayList;
import java.util.List;

class Solution {
    
    static class Time {
		double start, end;

		public Time(double start, double end) {
			this.start = start;
			this.end = end;
		}
		
	}
    
    public int solution(String[] lines) {
        int answer = 1;
		List<Time> times = new ArrayList<>();
		
		//분리를 하자
		for(String info : lines) {
			String[] splitInfo = info.split(" ");
			String requestCompleteTime = splitInfo[1]; //응답완료시간
			String processTime = splitInfo[2]; //처리시간
			
			double end = calcTimeNum(requestCompleteTime);
			
			double start = end - (Double.parseDouble(processTime.substring(0, processTime.length() - 1)) - 0.001);
			
			if(start < 0) start = 0;
			
			times.add(new Time(start, end));
			
		}
		
		for(int i=0; i < times.size() - 1; i++) {
			double end = times.get(i).end + 1;
			int cnt = 1;
			for(int j= i + 1; j < times.size(); j++) {
				Time temp = times.get(j);
				if(end > temp.start) cnt++;
			}
			answer = Math.max(answer, cnt);
		}
		
        return answer;
    }
	
	//시간을 초단위 숫자로 변환해주는 함수
	private static double calcTimeNum(String time) {
		String[] timeInfo = time.split(":");
		double totalTime = 0;
		totalTime += Double.parseDouble(timeInfo[0]) * 3600; //시간을 초단위로 변환
		totalTime += Double.parseDouble(timeInfo[1]) * 60; //분을 초단위로 변환
		totalTime += Double.parseDouble(timeInfo[2]); //초단위 변환
		return totalTime;
	}
}