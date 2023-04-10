import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static Boolean[][] roadWay; 
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        //초기화
		roadWay = new Boolean[102][102];
		
		//2배로 증가
		characterX *= 2;
		characterY *= 2;
		itemX *= 2;
		itemY *= 2;
		
		//지형 표시하기
		for(int i=0; i < rectangle.length; i++) goTopography(rectangle[i][0] * 2, rectangle[i][1] * 2, rectangle[i][2] * 2, rectangle[i][3] * 2);
		
        int[] dx1 = new int[] {1, -1, 0, 0};
        int[] dy1 = new int[] {0, 0, 1, -1};
		
        int answer = 0;
        //시뮬레이션 돌리기
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(characterX, characterY));
        
        roadWay[characterX][characterY] = false;
        
        int nx, ny;
        
        
        Point cur;
        total : while(!q.isEmpty()) {
        	int size = q.size();
        	for(int i=0; i < size; i++) {
        		cur = q.poll();
        		
        		//아이템을 만났을 때 반복문 종료
        		if(cur.x == itemX && cur.y == itemY) break total;
        		
        		for(int d=0; d < 4; d++) {
        			nx = cur.x + dx1[d];
        			ny = cur.y + dy1[d];
        			if(outMap(nx, ny) || roadWay[nx][ny] != Boolean.TRUE) continue;
        			roadWay[nx][ny] = false;
        			q.offer(new Point(nx, ny));
        		}
        		
        	}
        	answer++;
        }
        
        
        return answer / 2;
    }
    
    	//지형 표시
	private static void goTopography(int startX, int startY, int endX, int endY) {
		for(int x=startX; x <= endX; x++) for(int y=startY; y <= endY; y++) roadWay[x][y] = (x == startX || x == endX || y == startY || y == endY) && roadWay[x][y] != Boolean.FALSE;
	}

	private static boolean outMap(int x, int y) {
		return (x < 2 || x > 100 || y < 2 || y > 100);
	}
}