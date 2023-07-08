/*
표 편집
선택, 삭제, 복구하는 프로그램 작성
한 번에 한 행만 선택할 수 있음.
U X -> 현재 선택된 행에서 X칸 위에 있는 행을 선택
D X -> 현재 선택된 행에서 X칸 아래에 있는 행을 선택
C -> 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택.
    단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택
Z -> 가장 최근에 삭제된 행을 원래대로 복구
    단, 현재 선택된 행은 바뀌지 않음.
*/
import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] pre = new int[n];
        int[] next = new int[n];
        for(int i = 0; i < n; i++) {
            pre[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;
        
        Stack<Node> rm = new Stack<>();
        StringBuilder answer = new StringBuilder("O".repeat(n));
        for(String s : cmd){
            switch(s){
                case "C" : //삭제
                    rm.push(new Node(pre[k], k, next[k]));
                    if(pre[k] != -1) next[pre[k]] = next[k]; //현재 노드 삭제 후 앞뒤 연결
                    if(next[k] != -1) pre[next[k]] = pre[k];
                    answer.setCharAt(k, 'X');

                    if(next[k] != -1) k = next[k];
                    else k = pre[k]; //마지막 행인 경우에 바로 윗 행 선택
                    break;
                case "Z" : //복구
                    Node node = rm.pop();
                    if(node.pre != -1) next[node.pre] = node.cur; //연결 정보 복구
                    if(node.nxt != -1) pre[node.nxt] = node.cur;
                    answer.setCharAt(node.cur, 'O');
                    break;
                default : //이동
                    int moveCnt = Integer.valueOf(s.substring(2));
                    if(s.charAt(0) == 'U') while(moveCnt-- > 0) k = pre[k]; //위로
                    else while(moveCnt-- > 0) k = next[k]; //아래로
                    break;
            }
        }
        return answer.toString();
    }
    
    public class Node{
        int pre, cur, nxt;
        
        public Node(int pre, int cur, int nxt) {
            this.pre = pre;
            this.cur = cur;
            this.nxt = nxt;
        }
    }
    
}