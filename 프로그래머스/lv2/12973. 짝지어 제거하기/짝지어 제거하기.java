class Solution
{
    static class ArrayStack {
		int top, size;
		char[] stack;
		public ArrayStack(int size) {
			this.size = size;
			this.stack = new char[size];
			this.top = -1;
		}
		public void push(char item) {
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
    
    public int solution(String s)
    {
       int answer = 0;

        char[] cList = s.toCharArray();
        ArrayStack stack = new ArrayStack(cList.length);
        
        for(int i=0; i < cList.length; i++) {
        	char c  = cList[i];
        	if(stack.isEmpty()) stack.push(c);
        	else {
        		if(stack.peek() == c) stack.pop();
        		else stack.push(c);
        	}
        	
        }

        if(stack.isEmpty()) answer = 1;
        
        return answer;
    }
}