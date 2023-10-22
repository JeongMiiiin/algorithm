import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] numList = new int [N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) numList[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(numList);
        
        int result = 0;
        int left, right;
        for (int i = 0; i < N; i++) {
            left = 0;
            right = N - 1;

            while (left < right) {
                if (numList[left] + numList[right] < numList[i]) left++;
                else if (numList[left] + numList[right] > numList[i]) right--;
                else {
                    if (left == i) left++;
                    else if (right == i) right--;
                    else {
                    	result++;
                        break;
                    }
                }
            }
        }

        System.out.println(result);
    }
}