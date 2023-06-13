import java.util.*;

class Solution {
    static Set<Integer> ansSet = new HashSet<>();
    static char[] numList, numTargets;
    static boolean[] visited;
    static int N, R;
    
    public int solution(String numbers) {
        ansSet.clear();
        
        numList = numbers.toCharArray();
        N = numList.length;
        
        
        for(int i=1; i <= N; i++){
            R = i;
            numTargets = new char[R];
        
            perm(0, 0);
        }
        
        int answer = ansSet.size();
        return answer;
    }
    
    private static void perm(int cnt, int flag){
        if(cnt == R){
            StringBuilder sb = new StringBuilder();
            for(char c : numTargets) sb.append(String.valueOf(c));
            
            int target = Integer.parseInt(sb.toString());
            if(checkPrime(target)) ansSet.add(target);
            
            return;
        }
        
        for(int i = 0; i < N; i++) {
			if((flag & 1 << i) != 0) continue;
			numTargets[cnt] = numList[i];
			perm(cnt + 1, flag | 1 << i);
		}
    }
    
    private static boolean checkPrime(int n){
        if(n == 0 || n == 1) return false;
        
        for(int i=2; i <= Math.sqrt(n); i++) if(n % i == 0) return false;
        
        return true;
    }
}