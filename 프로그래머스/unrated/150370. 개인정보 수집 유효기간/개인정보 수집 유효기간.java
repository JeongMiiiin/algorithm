import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*	
 * 프로그래머스 - 개인정보 수집 유효기간
 * 
 * 주어지는 값
 * today : 오늘 날짜를 의미
 * terms : 약관들의 유효기간
 * privacies : 개인정보의 정보
 * 
 * 고객의 약관 동의를 얻어서 수집된 1 ~ n번으로 분류되는 개인정보 n개가 있음
 * 약관의 종류는 여러가지가 있고, 각 약관마다 개인정보 보관 유효기간이 정해져 있다.
 * 수집된 개인정보는 유효기간 전까지만 보관 가능하며, 유효기간이 지났다면 반드시 파기해야 함
 * 
 * 모든 달은 28일까지 있다고 가정
*/

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		
		Date standard = formatter.parse(today);
		
		//개인정보 세팅
		Map<String, Integer> expirationPeriod = new HashMap<>();
		for(int i=0; i < terms.length; i++) {
			String[] temp = terms[i].split(" ");
			expirationPeriod.put(temp[0], Integer.parseInt(temp[1]));
		}
		
		int[] answer = new int[privacies.length];
		
		int idx = 0;
		//유효정보 계산
		for(int i=0; i < privacies.length; i++) {
			String[] temp = privacies[i].split(" ");
			Date expiration = formatter.parse(temp[0]); //생성날짜
			int period = expirationPeriod.get(temp[1]); //유효기간 가져오기
			expiration.setMonth(expiration.getMonth() + period); //최대 유효기간으로 설정
			int possible = standard.compareTo(expiration); //date 비교
			if(possible > -1) answer[idx++] = i + 1; //standard가 더 크면 만료된 유효정보라 생각하고 추가
		}
		
		answer = Arrays.copyOfRange(answer, 0, idx);
		
        return answer;
    }
}