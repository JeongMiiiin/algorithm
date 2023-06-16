import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 10;
        int[] arr = new int[N + 1];
        int[] sum = new int[N + 1]; // 새로운 배열 생성

        // init
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            sum[i] = sum[i - 1] + arr[i]; // 누적합 배열을 초기화 시켜준다.
        }

        int result = 0;
        
        for(int num : sum){
            int tmpNum = Math.abs(num - 100);
            int tmpResult = Math.abs(result - 100);

            // result 보다 100과의 거리가 멀다면 다음으로
            if(tmpResult < tmpNum ) continue;

            result = num;
        }

        System.out.println(result);
    }
}