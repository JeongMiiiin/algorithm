import java.util.Scanner;

/*
 * 백준 13305 - 주유소
 * 어떤 나라에 N개의 도시가 있음.
 * 이 도시들은 일직선 도로 위에 있다.
 * 제일 왼쪽의 도시에서 제일 오른쪽의 도시로 자동차를 이용하여 이동하려고 함.
 * 인접한 두 도시 사이의 도로들은 서로 길이가 다를 수 있다.
 * 도시마다 주유소가 있으며, 리터당 가격은 다를 수 있다.
 * 가장 최소의 비용으로 제일 오른쪽의 도시로 이동하라
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[] roads = new long[N - 1];
		for(int i=0; i < N - 1; i++) roads[i] = sc.nextInt();
		int[] fuels = new int[N];
		for(int i=0; i < N; i++) fuels[i] = sc.nextInt();
		long result = 0;
		int idx = 0, cur = Integer.MAX_VALUE;
		while(idx < N - 1) {
			if(cur > fuels[idx]) {
				cur = fuels[idx];
			}
			result += cur * roads[idx++];
		}
		
		System.out.println(result);
		sc.close();
	}
}