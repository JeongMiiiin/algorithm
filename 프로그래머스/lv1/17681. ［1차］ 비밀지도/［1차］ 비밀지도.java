class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        StringBuilder sb = new StringBuilder();
        int c1Idx, c2Idx;
        for(int i=0; i < n; i++){
            sb.setLength(0);
            char[] cList1 = Integer.toBinaryString(arr1[i]).toCharArray();
            char[] cList2 = Integer.toBinaryString(arr2[i]).toCharArray();
            c1Idx = cList1.length - 1;
            c2Idx = cList2.length - 1;
            while(c1Idx >= 0 || c2Idx >= 0){
                if((c1Idx >= 0 && cList1[c1Idx] == '1') || (c2Idx >= 0 && cList2[c2Idx] == '1')) sb.insert(0, "#");
                else sb.insert(0, " ");
                c1Idx--;
                c2Idx--;
            }
            while(sb.length() < n) sb.insert(0, " ");
            
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}