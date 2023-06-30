import java.util.StringTokenizer;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s, " ", true);
        StringBuffer sb = new StringBuffer();
        while(st.hasMoreTokens()){
            String str = st.nextToken();
            if(str.startsWith(" ")) {
                sb.append(str);
                continue;
            }
            String first = str.substring(0, 1);
            if(!isInteger(first)) first = first.toUpperCase();
            String remain = "";
            if(str.length() > 1) remain = str.substring(1, str.length());
            
            sb.append(first + remain.toLowerCase());
        }
        return sb.toString();
    }
    
    private static boolean isInteger(String strValue) {
        try {
          Integer.parseInt(strValue);
          return true;
        } catch (NumberFormatException ex) {
          return false;
        }
      }
}