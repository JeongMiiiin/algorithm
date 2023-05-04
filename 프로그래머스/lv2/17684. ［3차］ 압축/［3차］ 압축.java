import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(String msg) {
        List<String> dictionary = new ArrayList<>();
		String[] init = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(String s : init) dictionary.add(s); 
		
		int[] answer = new int[msg.length()];
		
		int answerIdx = 0;
		
		char[] cList = msg.toCharArray();
		int idx = 0;
		while(idx < cList.length) {
			String target = String.valueOf(cList[idx]);
			int targetNum = dictionary.indexOf(target);
			int nextIdx = 1;
			boolean flag = false;
			//해당 문자를 포함하고 있을 때
			while(targetNum > -1) {
				if(idx + nextIdx >= cList.length) break;
				target += String.valueOf(cList[idx + nextIdx++]);
				//새로운 문자열을 봤을 때
				if(dictionary.indexOf(target) == -1) {
					dictionary.add(target);
					flag = true;
					break;
				} else targetNum = dictionary.indexOf(target); 
			}
			
			if(flag) idx += target.length() - 1;
			else idx += target.length();
			
			answer[answerIdx++] = targetNum + 1;
		}
		
		answer = Arrays.copyOf(answer, answerIdx);
        return answer;
    }
}