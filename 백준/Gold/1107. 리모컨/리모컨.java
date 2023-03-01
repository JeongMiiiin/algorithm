import java.util.Scanner;

/*
 * 백준 1107번 - 리모컨
 * 주어지는 값
 * N : 이동해야하는 채널
 * M : 고장난 버튼의 개수
 * 세번째 줄 : 고장난 버튼들의 정보
 * 현재 100번에서 N번까지 최소로 버튼을 움직여 이동하라
 * 
*/
public class Main {
	static boolean[] disable = new boolean[10];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        boolean[] disable = new boolean[10];
        for(int i = 0; i < M; i++) {
            int n = sc.nextInt();
            disable[n] = true;
        }
        
        int result = Math.abs(N - 100); //초기값 설정
        for(int i = 0; i <= 999999; i++) {
            String str = String.valueOf(i);
            int len = str.length();
            
            boolean isBreak = false;
            for(int j = 0; j < len; j++) {
                if(disable[str.charAt(j) - '0']) { //고장난 버튼을 눌러야 하면
                    isBreak = true; 
                    break; //더 이상 탐색하지 않고 빠져나온다.
                }
            }
            if(!isBreak) { //i를 누를때 고장난 버튼을 누르지 않는다면
                int min = Math.abs(N - i) + len; //i를 누른 후(len) target까지 이동하는 횟수(target - i)
                result = Math.min(min, result);
            }
        }        
        System.out.println(result);
        sc.close();
    }
}
