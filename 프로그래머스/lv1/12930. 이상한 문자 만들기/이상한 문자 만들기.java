class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder(s);
        int answerIdx = 0;
        for(int i=0; i < s.length(); i++){
            if(s.charAt(i) != ' '){
                int num = (int) s.charAt(i);
                if(answerIdx % 2 == 0 && num >= 97 && num <= 122) sb.setCharAt(i, (char) (s.charAt(i) - 32));
                else if(answerIdx % 2 == 1 && num >= 65 && num <= 90 ) sb.setCharAt(i, (char) (s.charAt(i) + 32));
                answerIdx++;
            } else answerIdx = 0;   
        }
        return sb.toString();
    }
}