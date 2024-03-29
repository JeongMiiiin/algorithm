class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        //가장 큰 단위가 2분의 1이기 때문에 해당 개수로 비교
        for(int i = 1; i <= s.length() / 2; i++){
            int zipLevel = 1;
            String zipStr = s.substring(0,i);
            StringBuilder result = new StringBuilder();
            
           for(int j = i; j <= s.length(); j += i){
                // 다음 문자 추출
                String next = s.substring(j, j + i > s.length() ? s.length() : i + j);
                // 다음 문자와 현재 문자가 같으면 zipLevel증가
                if(zipStr.equals(next)) zipLevel++;
                // 다음 문자와 현재 문자가 다를 경우
                else{
                    // (압축이 안되었을 경우는 공백, 압축이 되었을경우 zipLevel을 붙여줌) + 압축할 문자를 넣어줌, 
                    result.append((zipLevel != 1 ? zipLevel : "") + zipStr);
                    zipStr = next;      // 다음 문자를 압축할 문자로 지정
                    zipLevel = 1;       // 압축 정도 1로 초기화
                }
            }
            result.append(zipStr);      // 마지막에 추가 안된 zipStr추가
            answer = Math.min(answer, result.length()); // 가장 작은 문자열 길이 저장
        }
        
        return answer;
    }
}