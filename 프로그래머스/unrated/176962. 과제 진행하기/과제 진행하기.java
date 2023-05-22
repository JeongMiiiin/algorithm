import java.util.*;
/*
과제 진행하기
규칙
1. 시작하기로 한 시각이 되면 시작
2. 새로운 과제를 시작할 시간이 되었을 때, 기존에 진행중이던 과제가 있다면 멈추고, 새로운 과제 시작
3. 진행중이던 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면, 멈춰둔 과제를 이어서 진행
3-1. 과제를 끝낸 시각에 새로 시작해야 되는 과제와 잠시 멈춰둔 과제가 모두 있다면, 새로 시작해야 하는 과제부터 진행
3-2. 멈춰둔 과제가 여러개일 경우, 가장 최근에 멈춘 과제부터 시작
*/

class Solution {
    static class Plan implements Comparable<Plan>{
        String title;
        int startTime, playTime, remainTime;
        public Plan(String title, int startTime, int playTime, int remainTime){
            this.title = title;
            this.startTime = startTime;
            this.playTime = playTime;
            this.remainTime = remainTime;
        }
        @Override
        public int compareTo(Plan o){
            return this.startTime - o.startTime;
        }
    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        PriorityQueue<Plan> pq = new PriorityQueue<>();
        for(String[] info : plans) pq.offer(new Plan(info[0], calcTime(info[1]), Integer.parseInt(info[2]), Integer.parseInt(info[2])));
        
        Stack<Plan> stack = new Stack<>();
        
        int curTime = 0;
        int idx = 0;
        while(!pq.isEmpty()){
            Plan cur = pq.poll();
            if(stack.size() > 0){ //진행중이었던 과제가 있을 때
                Plan prev = stack.peek();
                int remain = (cur.startTime - curTime);
                //과제를 끝낼 수 있을 때
                while(stack.size() > 1 && prev.remainTime - remain < 0){
                    remain -= prev.remainTime;
                    answer[idx++] = prev.title;
                    stack.pop();
                    prev = stack.peek();
                }
                prev.remainTime -= remain;
                if(prev.remainTime <= 0){
                    answer[idx++] = stack.pop().title;
                }
                stack.push(cur);
                
            } else stack.push(cur);
            curTime = cur.startTime;
        }
        
        while(!stack.isEmpty()) answer[idx++] = stack.pop().title;
        
        return answer;
    }
    
    private static int calcTime(String time){
        String[] info = time.split(":");
        return (Integer.parseInt(info[0]) * 60 + Integer.parseInt(info[1]));
    }
}