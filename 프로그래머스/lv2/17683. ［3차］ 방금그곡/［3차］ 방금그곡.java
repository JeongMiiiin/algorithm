import java.util.PriorityQueue;

/*
 * 프로그래머스 - 방금 그곡
 * 
 * 자신이 기억한 멜로디를 가지고 방금 그곡을 이용해 음악을 찾는다.
 * 그런데 한 음악을 반복해서 재생할 때도 있어서 기억하고 있는 멛로디가 음악 끝부분과 처음 부분이 이어서 재생된 멜로디일 수 있다.
 * 반대로, 한 음악을 중간에 끊을 경우 원본 음악에는 네오가 기억한 메돌리가 들어있다 해도 그 곡이 네오가 들은 곡이 아닐 수도 있다.
 * 그렇기 때문에 네오는 기억한 멜로디를 재생 시간과 제공된 악보를 직접 보면서 비교하려고 한다.
 * 방금 그곡 서비스에서는 음악 제목, 재생이 시작되고 끝난 시각, 악보를 제공함
 * 사용되는 음 -> C, C#, D, D#, E, F, F#, G, G#, A, A#, B (12개)
 * 각 음은 1분에 1개씩 재생됨. 음악은 반드시 처음부터 재생됨
 * 음악 길이보다 재생된 시간이 길 때는 음악이 끊김 없이 처음부터 반복 재생
 * 음악 길이보다 재생된 시간이 짧을 때는 재생시간만큼만 재생
 * 음악이 00:00를 넘겨서까지 재생되는 일은 없다.
 * 조건이 일치하는 음악이 여러 개일때는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환.
 * 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환
 * 조건이 일치하는 음악이 없을 때에는 "(None)"을 반환
 * 
*/

class Solution {
    static class Music implements Comparable<Music>{
		int plays;
		String info, title;
		int order;
		
		public Music(int plays, String info, String title, int order) {
			this.plays = plays;
			this.info = info;
			this.title = title;
			this.order = order;
		}

		@Override
		public int compareTo(Music o) {
			return o.plays - this.plays != 0 ? o.plays - this.plays : this.order - o.order;
		}
		
	}
    
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        
        PriorityQueue<Music> musics = new PriorityQueue<>();
        int idx = 1;
        for(String s : musicinfos) {
        	String[] infos = s.split(",");
        	int plays = changeTimeFromStringToInt(infos[1]) - changeTimeFromStringToInt(infos[0]);
        	String info = playsInfo(plays, infos[3]);
        	musics.add(new Music(plays, info, infos[2], idx++));
        }
        
        //확인
        Music cur;
        outer : while(!musics.isEmpty()) {
        	cur = musics.poll();
        	while(cur.info.indexOf(m) > -1) { //악보를 포함하고 있을 때
        		int check = cur.info.indexOf(m);
        		
        		if(cur.info.length() > check + m.length() + 1) {
        			String next = cur.info.substring(check + m.length(), check + m.length() + 1);
            		if(next.equals("#")){
            			cur.info = cur.info.substring(check + m.length() + 1);
            			continue;
            		} else {
            			answer = cur.title;
            			break outer;
            		}
        		} else {
        			answer = cur.title;
        			break outer;
        		}
        	}
        }
        
        return answer;
    }
	
	private static String playsInfo(int plays, String info) {
		StringBuffer result = new StringBuffer();
		char[] infoList = info.toCharArray();
		int idx = 0, start = 0;
		while(idx++ < plays) {
            if(start == infoList.length) start = 0;
			char c = infoList[start++];
			result.append(String.valueOf(c));
			if(start == infoList.length) {
				start = 0;
				continue;
			}
			if(c == 'C' || c == 'D' || c == 'F' || c == 'G' || c == 'A') {
				if(infoList[start] == '#') {
					c = infoList[start++];
					result.append(String.valueOf(c));
				}
			}
		}
		
		return result.toString();
	}
	
	
	//시간 문자를 분 숫자로 변경
	private static int changeTimeFromStringToInt(String s) {
		String[] timeInfo = s.split(":");
		int result = 0;
		
		result += Integer.parseInt(timeInfo[0]) * 60;
		result += Integer.parseInt(timeInfo[1]);
		
		return result;
	}
}