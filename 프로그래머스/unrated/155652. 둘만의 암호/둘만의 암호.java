class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        for(char c : s.toCharArray()){
            int real = 0;
            while(real < index){
                c = (char) (c + 1);
                if(c == '{') c = 'a';
                if(skip.indexOf(String.valueOf(c)) == -1) real++;
            }
            answer.append(c);
        }
        
        return answer.toString();
    }
}