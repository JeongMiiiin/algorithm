import java.util.Arrays;

class Solution {
    public int[] parent;
    
    public int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) 
            parent[b] = a;
    }
    
    public int solution(int n, int[][] costs) {
        //초기화
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        
        //크루스칼 알고리즘 
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        int result = 0;
        for (int i = 0; i < costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int val = costs[i][2];
            if (find(a) != find(b)) {
                union(a, b);
                result += val;
            }
        }
        
        return result;
    }
}