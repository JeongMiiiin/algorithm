import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {
    static StringTokenizer st;
    public String[] solution(String[] record) {
		Map<String,String> userList = new HashMap<>();
		
		int changeCnt = 0;
		String cmd;
		for(String s : record) {
			st = new StringTokenizer(s, " ");
			cmd = st.nextToken();
			if( !cmd.equals("Leave") ) userList.put(st.nextToken(), st.nextToken());
			if(cmd.equals("Change")) changeCnt++;
		}
		
		String[] answer = new String[record.length - changeCnt];
		
		String userName;
		for(int i=0, answerIdx = 0; i < record.length; i++) {
			st = new StringTokenizer(record[i], " ");
			cmd = st.nextToken();
			if(cmd.equals("Change")) continue; //변경 기록인 경우 패스
			userName = st.nextToken();
			String info = "님이 들어왔습니다.";
			//나간 기록인 경우
			if( !cmd.equals("Enter") ) info = "님이 나갔습니다.";
			
			answer[answerIdx++] = userList.get(userName) + info;
		}
		
		return answer;
	}
}