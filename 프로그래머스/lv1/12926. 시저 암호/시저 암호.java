class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c != ' '){
              int target = c + n;
              if((c < 91 && c + n > 90) || (c < 123 && c + n > 122)) target -= 26;
              sb.append((char) target);  
            } else sb.append(" ");
        }
        return sb.toString();
    }
}