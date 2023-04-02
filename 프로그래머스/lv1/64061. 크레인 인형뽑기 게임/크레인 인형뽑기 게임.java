import java.util.*;

class Solution {
    static class ArrayStack {
		int top, size;
		int[] stack;
		public ArrayStack(int size) {
			this.size = size;
			this.stack = new int[size];
			this.top = -1;
		}
		public void push(int item) {
			stack[++top] = item;
		}
		public int pop() {
			int pop = stack[top];
			stack[top--] = 0;
			return pop;
		}
		public int peek() {
			return stack[top];
		}
		public int size() {
			return top + 1;
		}
		public boolean isEmpty() {
			return top + 1 == 0;
		}
	}
    
    
    public int solution(int[][] board, int[] moves) {
        
        int answer = 0;
        
        ArrayStack crain = new ArrayStack(moves.length);
        
        int len = board.length;
        
        //움직임 실행
        for(int targetIdx : moves){
            targetIdx--;
            
            //해당 칸에 잡을 인형 찾기
            int catchDoll = 0;
            for(int i=0; i < len; i++) {
            	if(board[i][targetIdx] != 0) { //인형이 잡혔을 때
            		catchDoll = board[i][targetIdx];
            		board[i][targetIdx] = 0; //해당 칸 빈칸으로 만들기
            		break;
            	}
            }
            //잡은 인형이 있을 때
            if(catchDoll > 0) {
            	//바구니가 비어있지 않고, 맨 위에 있는 인형과 같은 종류일 때 삭제 진행
            	if( !crain.isEmpty() && crain.peek() == catchDoll) { 
            		crain.pop();
            		answer += 2;
            	} else crain.push(catchDoll);
            }
        }
        
        return answer;
    }
}