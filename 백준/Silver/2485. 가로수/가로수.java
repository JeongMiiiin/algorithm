import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int first = sc.nextInt();
		int temp = first, cur = first;
		int[] durationList = new int[N - 1];
		for(int i=1; i < N - 1; i++) {
			temp = sc.nextInt();
			durationList[i - 1] = temp - cur;
			cur = temp;
		}
		int last = sc.nextInt();
		durationList[N - 2] = last - cur;
		
		int duration = 1;
		int tempDuration = 1;
		outer : while(true) {
			boolean flag = true;
			tempDuration++;
			for(int i=0; i < N - 1; i++) {
				int share = durationList[i] / tempDuration;
				if(share == 0) break outer;
				
				int remain = durationList[i] % tempDuration;
				if(remain > 0) {
					flag = false;
					break;
				}
			}
			
			if(flag) duration = tempDuration;
		}
		
		int range = last - first;
		int share = range / duration;
		System.out.println(share + 1 - N);
		sc.close();
	}
}