class Solution {
    public String solution(int[] food) {
        //초기 세팅 구성
        StringBuilder sb = new StringBuilder("0");
        for(int i= food.length - 1; i > 0; i--){
            int share = food[i] / 2;
            int cnt = 0;
            while(cnt ++ < share){
                sb.insert(0, i);
                sb.append(i);
            }
        }
        return sb.toString();
    }
}