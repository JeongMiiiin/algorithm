import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    static Map<String, Integer> candidates = new HashMap<>();
	static char[] inputs;
	static char[] numbers;
	static int N, R;
    public String[] solution(String[] orders, int[] course) {
        candidates.clear();
		
		//주문 내역
		for(String s : orders) {
			inputs = s.toCharArray();
            Arrays.sort(inputs);
			N = inputs.length;
			for(int n : course) {
				if(n > inputs.length) continue;
				R = n;
	        	numbers = new char[R];
	        	comb(0, 0);
	        }
		}
		
		//개수세기
		int realSize = 0;
        String[] answer = new String[candidates.size()];
        for(int n : course) {
        	int max = 2;
        	List<String> tempList = new ArrayList<>();
        	for(Map.Entry<String, Integer> pair : candidates.entrySet()) {
        		if(pair.getKey().length() == n) {
        			if(max < pair.getValue()) {
        				max = pair.getValue();
        				tempList.clear();
        				tempList.add(pair.getKey());
        			} else if (max == pair.getValue()) tempList.add(pair.getKey());
        		}
        	}
        	
        	for(String s : tempList) answer[realSize++] = s;
        }
        
        
        answer = Arrays.copyOf(answer, realSize);
        Arrays.sort(answer);
        return answer;
    }
	
	
	private static void comb(int cnt, int start) {
		if(cnt == R) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < R; i++) sb.append(numbers[i]);
			candidates.put(sb.toString(), candidates.getOrDefault(sb.toString(), 0) + 1);
			return;
		}
		
		for(int i=start; i < N; i++) {
			numbers[cnt] = inputs[i];
			comb(cnt + 1, i + 1);
		}
	}
}