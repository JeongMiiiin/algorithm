import java.util.PriorityQueue;

class Solution {
    class Text implements Comparable<Text> {
        String s;
        char c;
        public Text(String s, char c){
            this.s = s;
            this.c = c;
        }
        @Override
        public int compareTo(Text o){
            return this.c != o.c ? this.c - o.c : this.s.compareTo(o.s);
        }
    }
    
    public String[] solution(String[] strings, int n) {
        PriorityQueue<Text> pq = new PriorityQueue<>();
        for(String s : strings) pq.add(new Text(s, s.charAt(n)));
        
        String[] answer = new String[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) answer[idx++] = pq.poll().s;
        return answer;
    }
}