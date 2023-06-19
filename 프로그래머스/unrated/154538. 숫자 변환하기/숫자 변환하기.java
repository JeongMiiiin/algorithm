import java.util.*;
import java.awt.Point;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;
        
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(y, 0));
        Point cur;
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur.x == x) return cur.y;
            else if (cur.x > x){
                q.add(new Point(cur.x - n, cur.y + 1));
                if(cur.x % 2 == 0) q.add(new Point(cur.x / 2, cur.y + 1));
                if(cur.x % 3 == 0) q.add(new Point(cur.x / 3, cur.y + 1));
            }
        }
        
        return answer;
    }
}