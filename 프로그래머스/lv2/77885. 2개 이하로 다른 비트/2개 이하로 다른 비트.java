class Solution {
    public long[] solution(long[] numbers) {
        int len = numbers.length;
        long[] answer = new long[len];
        for(int i=0; i < len; i++){
            if(numbers[i] % 2 == 0) answer[i] = numbers[i] + 1;
            else answer[i] = find(numbers[i]);
        }
        return answer;
    }
    
    private static long find(long n){
        String s = Long.toString(n, 2);
        StringBuilder sb = new StringBuilder(s);
        int lastIdx = sb.length() - 1;
        while(lastIdx >= 0 && sb.charAt(lastIdx) != '0') lastIdx--;
        
        if(lastIdx >= 0) sb.setCharAt(lastIdx, '1');
        else {
            sb.insert(0, "1");
            lastIdx++;
        }
        for(int i=0; i < sb.length(); i++) sb.setCharAt(i, '0');
        sb.setCharAt(lastIdx, '1');
        
        Long n2 = Long.parseLong(sb.toString(), 2);
        
        return Long.parseLong(s, 2) + (n2 / 2);
    }
}