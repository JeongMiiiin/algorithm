import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 프로그래머스 - 가장 먼 노드
 * 
 * n개의 노드가 있는 그래프
 * 각 노드는 1 ~ n번까지 번호가 있음.
 * 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려 한다.
 * 
*/

class Solution {
    static class Node {
		int num;
		List<Integer> nodeList;
		public Node(int num) {
			this.num = num;
			this.nodeList = new ArrayList<>();
		}
		
	}
    
    public int solution(int n, int[][] edge) {
        //초기 세팅
		Node[] nodes = new Node[n + 1];
		for(int i=1; i <= n; i++) nodes[i] = new Node(i);
		
		boolean[] visited = new boolean[n + 1];
		
		
		
		//간선 세팅
		for(int[] vertex : edge) {
			nodes[vertex[0]].nodeList.add(vertex[1]);
			nodes[vertex[1]].nodeList.add(vertex[0]);
		}
		
		Queue<Integer> temp = new LinkedList<>();
		
		for(int num : nodes[1].nodeList) temp.add(num);
		
		visited[1] = true;
		
		int answer = 0;
		
		while(!temp.isEmpty()) {
			int size = temp.size();
			int cnt = 0;
			for(int i=0; i < size; i++) {
				int cur = temp.poll();
				if(!visited[cur]) {
					visited[cur] = true;
					for(int num : nodes[cur].nodeList) temp.add(num);
					cnt++;
				}
			}
			if(cnt > 0) answer = cnt;
		}
        
        return answer;
    }
}