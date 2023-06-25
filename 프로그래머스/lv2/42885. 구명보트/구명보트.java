import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        //정렬
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;
        int answer = 0;
        
        while(start <= end){
            if(start == end){
                answer++;
                break;
            }
            int weight = people[start] + people[end--];
            if(weight <= limit) start++;
            answer++;
        }
        return answer;
    }
}