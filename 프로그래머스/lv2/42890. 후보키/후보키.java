import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 프로그래머스 - 후보키
 * 
 * 후보키의 조건
 * 유일성
 * 최소성
 * 
*/
class Solution {
    
    static int[] candidates;
	static Set<String> candidateKey = new HashSet<>();
    public int solution(String[][] relation) {
        candidateKey.clear();
		
		int column = relation[0].length;
		candidates = new int[column];
		for(int i=0; i < column; i++) candidates[i] = i;
		
		List<Integer> numbers = new ArrayList<>();
		for(int i=0; i < (1 << candidates.length); i++) {
			numbers.clear();
			for(int j=0; j < candidates.length; j++) if((i & (1 << j)) != 0) numbers.add(candidates[j]);
			
			check(relation, numbers);
			
		}
		
        return candidateKey.size();
    }

	private static void check(String[][] relation, List<Integer> numbers) {

		Set<String> temp = new HashSet<>();
		
		for(int i=0; i < relation.length; i++) {
			String s = "";
			for(int j=0; j < relation[i].length; j++) if(numbers.contains(j)) s += relation[i][j] + " ";
			
			temp.add(s);
		}
		
		//유일한 키 조건에 부합하지 않을 때 종료
		if(temp.size() != relation.length) return;
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < (1 << numbers.size()); i++) {
			sb.setLength(0);
			for(int j=0; j < numbers.size(); j++) if((i & (1 << j)) != 0) sb.append(numbers.get(j));
			
			//안에 후보키가 포함되어있는 경우 종료
			if(candidateKey.contains(sb.toString())) return;
		}
		
		candidateKey.add(sb.toString());
	}
}