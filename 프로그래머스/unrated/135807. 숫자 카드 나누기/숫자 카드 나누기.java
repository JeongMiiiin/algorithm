class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int temp = 2;
        //조건 1에 부합하는 숫자 확인
        while(temp <= arrayA[0]){
            boolean flag = true;
            for(int n : arrayA){
                if(n % temp != 0){
                    flag = false;
                    break;
                }
            }
            
            if(flag){
                boolean flag2 = true;
                for(int n : arrayB){
                    if(n % temp == 0){
                        flag2 = false;
                        break;
                    }
                }
                if(flag2) answer = Math.max(answer, temp);
            }
            
            temp++;
        }
        
        temp = 2;
        //조건 2에 부합하는 숫자 확인
        while(temp <= arrayB[0]){
            boolean flag = true;
            for(int n : arrayB){
                if(n % temp != 0){
                    flag = false;
                    break;
                }
            }
            
            if(flag){
                boolean flag2 = true;
                for(int n : arrayA){
                    if(n % temp == 0){
                        flag2 = false;
                        break;
                    }
                }
                if(flag2) answer = Math.max(answer, temp);
            }
            
            temp++;
        }
        
        return answer;
    }
}