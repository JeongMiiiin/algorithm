class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        long standard = Long.parseLong(p);
        StringBuilder sb = new StringBuilder();
        char[] cList = t.toCharArray();
        for(int i=0; i <= cList.length - p.length(); i++){
            sb.setLength(0);
            for(int j=0; j < p.length(); j++) sb.append(cList[i + j]);
            if(Long.parseLong(sb.toString()) <= standard) answer++;
        }
        
        return answer;
    }
}