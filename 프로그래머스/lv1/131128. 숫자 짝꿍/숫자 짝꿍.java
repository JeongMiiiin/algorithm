import java.util.Arrays;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[10];

        for(int i =0; i<Y.length(); i++) arr[Y.charAt(i)-48]++;
        
        int zero = arr[0];
        for(int i =0; i<X.length(); i++) {
            int n = X.charAt(i)-48;
            if(arr[n]>0) {
                arr[n]--;
                sb.append(n);
            }
        }

        if(sb.length() == 0) return "-1";
        
        if(sb.length()==zero) return "0";
        
        String[] str = sb.toString().split("");
        Arrays.sort(str);
        sb.setLength(0);
        for(int i=str.length-1; i>=0; i--) sb.append(str[i]);

        return sb.toString();
    }
}