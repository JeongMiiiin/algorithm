/*
부대복귀
두 지역 간 이동 소요시간은 1

임무를 수행한 부대원은 지도 정보를이용하여 최단시간으로 부대에 복귀하고자 함.
다만, 적군의 방해로 임무의 시작 때와 다르게 되돌아오는 경로가 없어져
복귀가 불가능한 부대원도 있을 수 있다.

총 지역의 수 : n
두 지역을 왕복할 수 있는 길 정보 : roads
부대원들이 위치한 곳 : sources
도착해야 하는 곳 : destination

*/

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        //맵 만들기
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int[] road : roads){
            int from = road[0];
            int to = road[1];
            List<Integer> fromTemp = map.getOrDefault(from, new ArrayList<>());
            fromTemp.add(to);
            map.put(from, fromTemp);
            List<Integer> toTemp = map.getOrDefault(to, new ArrayList<>());
            toTemp.add(from);
            map.put(to, toTemp);
        }
        
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i < sources.length; i++){
            int source = sources[i];
            //이미 도달한 경우
            if(source == destination){
                answer[i] = 0;
                continue;
            }
            Arrays.fill(visited, false);
            q.clear();
            int cnt = 0;
            boolean flag = false;
            visited[source] = true;
            q.add(source);
            outer : while(!q.isEmpty()){
                int size = q.size();
                for(int j=0; j < size; j++){
                    int cur = q.poll();
                    List<Integer> temps = map.get(cur);
                    if(temps == null) continue; //비어있을 때
                    for(Integer temp : temps){
                        if(!visited[temp]){
                            visited[temp] = true;
                            if(temp == destination){
                                flag = true;
                                cnt++;
                                break outer;
                            }
                            q.add(temp);
                        }
                    }
                }
                cnt++;
            }
            
            if(flag) answer[i] = cnt; //도달할 때
            else answer[i] = -1; //도달하지 못할 때
        }
        
        return answer;
    }
}