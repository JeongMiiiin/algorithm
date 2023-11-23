import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[] attendList = new boolean[31];
		for(int i=0; i < 28; i++) attendList[sc.nextInt()] = true;
		
		int cnt = 0;
		while(cnt++ < 2) {
			int result = 0;
			for(int i=1; i <= 30; i++) {
				if(!attendList[i]) {
					attendList[i] = true;
					result = i;
					break;
				}
			}
			System.out.println(result);
		}
		sc.close();
	}
}