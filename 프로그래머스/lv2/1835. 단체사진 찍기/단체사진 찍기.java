// 8개 알파벳
import java.util.Arrays;

class Solution {
    class Condition {
        char start, end;
        int status, range; //status 0 -> 같음, 1 -> 미만, 2 -> 초과
        public Condition(char start, char end, int status, int range){
            this.start = start;
            this.end = end;
            this.status = status;
            this.range = range;
        }
    }
    
    //넣을 수 있는 알파벳 배열
    static int answer, N = 8;
    static String[] inputs = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static String[] numbers = new String[8];
    static boolean[] visited = new boolean[8];
    static Condition[] conditions;
    public int solution(int n, String[] data) {
        answer = 0;
        conditions = new Condition[data.length];
        for(int i=0; i < data.length; i++){
            char[] cList = data[i].toCharArray();
            
            int status = 0;
            if(cList[3] == '<') status = 1;
            else if(cList[3] == '>') status = 2;
            
            conditions[i] = new Condition(cList[0], cList[2], status, cList[4] - '0');
        }
        
        
        perm(0);
        
        return answer;
    }
    
    private static void perm(int cnt){
        if(cnt == N){
            //조건에 해당하면
            if(check()) answer++;
            return;
        }
        
        for(int i=0; i < N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            numbers[cnt] = inputs[i];
            perm(cnt + 1);
            numbers[cnt] = null;
            visited[i] = false;
        }
    }
    
    private static boolean check(){
        for(int i=0; i < conditions.length; i++){
            int startIdx = Arrays.asList(numbers).indexOf(Character.toString(conditions[i].start));
            int endIdx = Arrays.asList(numbers).indexOf(Character.toString(conditions[i].end));
            //비교할 문자들이 아직 전부 나와있지 않은 경우
            if(startIdx == -1 || endIdx == -1) continue;
            
            int range = conditions[i].range + 1;
            
            switch(conditions[i].status){
                case 1 : //미만
                    if(startIdx + range <= endIdx || startIdx - range >= endIdx) return false;
                    break;
                case 2 : //초과
                    if(startIdx + range >= endIdx && startIdx - range <= endIdx) return false;
                    break;
                default : //같음
                    if(startIdx + range != endIdx && startIdx - range != endIdx){
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}