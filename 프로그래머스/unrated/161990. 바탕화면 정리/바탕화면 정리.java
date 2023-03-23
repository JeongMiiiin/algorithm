class Solution {
    public int[] solution(String[] wallpaper) {
        int N = wallpaper.length; //행 길이
        int M = wallpaper[0].length(); //열 길이
        
        //상 좌는 계속 min값으로 갱신, 우 하는 계속 max값으로 갱신
        int left = 51, top = 51, right = -1, bottom = -1;
        
        for(int i=0; i < N; i++) {
        	char[] c = wallpaper[i].toCharArray();
        	for(int j=0; j < M; j++) {
        		if(c[j] == '#') { //파일이 있을 때 갱신
        			top = Math.min(top, i);
        			left = Math.min(left, j);
        			bottom = Math.max(bottom, i);
        			right = Math.max(right, j);
        		}
        	}
        }
        
        int[] answer = {top, left, bottom + 1, right + 1};
        
        return answer;
    }
}