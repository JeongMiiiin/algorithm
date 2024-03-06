import java.util.Scanner;

public class Main {
	static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        arr = new int[N];
        
        int max = 0;
        for (int i = 0; i < N; ++i) {
            arr[i] = sc.nextInt();
            max = Integer.max(max, arr[i]);
        }
        int result = 0;
        int left = max;
        int right = 10_000 * 100_000;
        // 이진 탐색을 이용하여 해답을 찾는다.
        while (left <= right) {
            int mid = (left + right) / 2;
            if (M >= getWithdrawalCount(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(result);
        sc.close();
    }
    /**
     * @param withdrawalAmount 현금 인출 금액
     * @return 돈을 계획대로 쓰기 위해 필요한 인출 횟수
     */
    static int getWithdrawalCount(int withdrawalAmount) {
        int count = 1;
        int money = withdrawalAmount;

        for (int i : arr) {
            money -= i;
            // 돈이 모자라면 현금을 다시 인출하여 사용
            if (money < 0) {
                ++count;
                money = withdrawalAmount - i;
            }
        }
        return count;
    }
}