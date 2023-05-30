import java.util.*;

/*
프로그래머스 - 호텔 대실

최소한의 객실만을 사용하여 예약 손님들을 받으려고 함.
한번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 한 후에 다음 손님들이 사용 가능

예약 시간이 주어졌을 때 필요한 최소 객실 수를 return
시작 시간을 정렬하고, 종료시간
*/

class Solution {
    static class Customer implements Comparable<Customer>{
        int startTime, endTime;
        public Customer(int startTime, int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }
        @Override
        public int compareTo(Customer o){
            return this.startTime != o.startTime ? this.startTime - o.startTime : this.endTime - o.endTime;
        }
    }
    public int solution(String[][] book_time) {
        //정렬 세팅
        PriorityQueue<Customer> pq = new PriorityQueue<>();
        for(String[] times : book_time) pq.add(new Customer(convertStringToTime(times[0]), convertStringToTime(times[1])));
        
        List<Customer> rooms = new ArrayList<>();
        Customer cur;
        while(!pq.isEmpty()){
            cur = pq.poll();
            boolean flag = false;
            for(int i=0; i < rooms.size(); i++){
                if(rooms.get(i).endTime + 10 <= cur.startTime){
                    flag = true;
                    rooms.set(i, cur);
                    break;
                }
            }
            if(!flag) rooms.add(cur);
        }
        
        int answer = rooms.size();
        return answer;
    }
    
    private static int convertStringToTime(String s){
        int result = 0;
        String[] info = s.split(":");
        result += Integer.parseInt(info[0]) * 60;
        result += Integer.parseInt(info[1]);
        return result;
    }
}