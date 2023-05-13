import java.util.*;
/*
프로그래머스 - 파일명 정렬
서버에는 프로그램의 과거 버전을 모두 담고 있어, 이름 순으로 정렬된 파일 목록을 보기 불편.
버전번호 9가 10보다 앞에 있기 때문
버전번호 외에도 숫자가 포함된 파일 목록은 보기 불편
파일명에 포함된 숫자를 반영한 정렬 기능을 저장소 관리 프로그램에 구현

영문 대소문자, 숫자, 공백 (" "), 마침표, 빼기 부호로 이루어짐.
파일명은 영문자로 시작하며, 숫자를 1개 이상 포함
크게 HEAD, NUMBER, TAIL로 구성
HEAD -> 숫자가 아닌 문자로 이루어져 있음.
NUMBER -> 1 ~ 5글자의 연속된 숫자로 이루어져 있고, 앞쪽에 0이 올수도 있음
TAIL -> 그 나머지 부분으로, 여기에는 숫자가 다시 나타날수도 있고, 아무 글자가 없을 수도 있음.
정렬 기준
HEAD 부분을 기준으로 사전순 정렬 (대소문자 구분 X)
NUMBER의 숫자 순으로 정렬
두 부분이 모두 같을 경우 원래 입력에 주어진 순서를 유지
*/

class Solution {
    static class FileInfo implements Comparable<FileInfo>{
        int idx;
        String head;
        int number;
        String info;
        
        public FileInfo(int idx, String head, int number, String info){
            this.idx = idx;
            this.head = head;
            this.number = number;
            this.info = info;
        }
        
        @Override
        public int compareTo(FileInfo o){
            //사전순 비교
            if(this.head.compareTo(o.head) != 0){
                return this.head.compareTo(o.head);
            } else if(this.number != o.number){ //number 비교
                return this.number - o.number;
            } else return this.idx - o.idx;
        }
    }
    
    public String[] solution(String[] files) {
        PriorityQueue<FileInfo> pq = new PriorityQueue<>();        
        //우선순위큐에 파일들 담기
        for(int i=0; i < files.length; i++){            
            
            char[] cList = files[i].toCharArray();
            String head = Character.toString(cList[0]);
            int idx = 1;
            for(idx=1; idx < cList.length; idx++){
                //문자를 만났을 경우
                if(!checkNum(cList[idx])) head += Character.toString(cList[idx]);
                else break; //숫자를 만났을 경우
            }
            String temp = "";            
            for(idx = idx; idx < cList.length; idx++){
                if(checkNum(cList[idx])) temp += Character.toString(cList[idx]);
                else break;
            }
            
            int number = Integer.parseInt(temp);
            pq.add(new FileInfo(i, head.toLowerCase(), number, files[i]));
        }
        
        String[] answer = new String[files.length];
        for(int i=0; i < files.length; i++){
            answer[i] = pq.poll().info;
        }
        
        
        return answer;
    }
    
    private static boolean checkNum(char c){
        return (c == '0' ||
                c == '1' ||
                c == '2' ||
                c == '3' ||
                c == '4' ||
                c == '5' ||
                c == '6' ||
                c == '7' ||
                c == '8' ||
                c == '9'
               );
    }
}