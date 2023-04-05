import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    static Map<String, Integer> str1List = new HashMap<>();
	static Map<String, Integer> str2List = new HashMap<>();
	static Set<String> totalSet = new HashSet<>();
    
    public int solution(String str1, String str2) {
       str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		divide(str1, 1);
		divide(str2, 2);
		
		int answer = 0, total = 0;
		for(String s : totalSet) total += Math.max(str1List.getOrDefault(s, 0), str2List.getOrDefault(s, 0));
		
		for(String s : str2List.keySet()) if(str1List.containsKey(s)) answer += Math.min(str1List.get(s), str2List.get(s));
		
		if(total == 0) return 65536;
        return answer * 65536 / total;
    }
    
    //부분집합 만들기
	private static void divide(String s, int num) {
		for(int i=0; i < s.length() - 1; i++) {
			String target = s.substring(i, i + 2);
			//추가할 수 있으면 추가하기
			if(judgeString(target)) {
				if(num == 1) str1List.put(target, str1List.getOrDefault(target, 0) + 1); //첫번째 부분집합에
				else str2List.put(target, str2List.getOrDefault(target, 0) + 1); //두번째 부분집합에
				totalSet.add(target); //합집합에 추가
			}
		}
	}

	//영문자로만 되어있는지 확인 a - 49 z - 74
	private static boolean judgeString(String s) {
		char[] c = s.toCharArray();
		
		int c1 = c[0] - '0';
		int c2 = c[1] - '0';
		
		if(c1 < 49 || c1 > 74) return false;
		if(c2 < 49 || c2 > 74) return false;
		
		return true;
	}
}