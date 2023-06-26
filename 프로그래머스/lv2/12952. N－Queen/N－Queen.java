class Solution {
    static int answer;
    static int N; // 배열의 크기
	static int[] col; //어디에 위치하는지 담아주는 배열
    public int solution(int n) {
        answer = 0;
        N = n;
        col = new int[N + 1];
        
        setQueen(1);
        
        return answer;
    }
    
    private static void setQueen(int rowNo) { //놓으려고 하는 퀸의 행 번호
		if( !isAvailable(rowNo - 1) ) return; //이전 번호들이 가능한 위치에 있는지 확인 후 불가능하면 return;
		
		if(rowNo > N) { //끝까지 왔다는 건 가능한 위치에 있다는 방증으로 ans 올려주고 return;
			answer++;
			return;
		}
		
		for(int c=1; c <= N; c++) {
			col[rowNo] = c;
			setQueen(rowNo + 1);
		}
		
	}
	private static boolean isAvailable(int rowNo) {
		for(int k=1; k < rowNo; k++) {
			if(col[k] == col[rowNo]) return false; //같은 행에 위치하고 있으면 탈락
			if(Math.abs(col[k] - col[rowNo]) == rowNo - k) return false; //대각선상에 위치하고 있으면 탈락
		}
		
		return true;
	}
    
}