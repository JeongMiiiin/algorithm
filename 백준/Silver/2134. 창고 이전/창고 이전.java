import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[m];
		for(int i=0; i < n; i++) a[i] = sc.nextInt();
		for(int i=0; i < m; i++) b[i] = sc.nextInt();
		long cnt = 0, cost = 0;
		int aIdx = 0, bIdx = 0;
		while(aIdx < n && bIdx < m) {
			if(a[aIdx] <= b[bIdx]) { //다 옮길 수 있을 때
				cnt += a[aIdx];
				cost += (aIdx + bIdx + 2) * a[aIdx];
				b[bIdx] -= a[aIdx];
				aIdx++;
			} else { //다 옮길 수 없을 때
				cnt += b[bIdx];
				cost += (aIdx + bIdx + 2) * b[bIdx];
				a[aIdx] -= b[bIdx];
				bIdx++;
			}
		}
		
		System.out.println(cnt + " " + cost);
		sc.close();
	}
}