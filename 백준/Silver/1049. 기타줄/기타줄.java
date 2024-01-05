import java.util.Scanner;

/*
 * 백준 1049 - 기타줄
 * N개의 줄이 끊어졌다. 따라서 새로운 줄을 사거나 교체해야 한다.
 * 되도록이면 돈을 적게 쓰려고 함
 * 6개 패키지를 살 수도 있고, 1개 또는 그 이상의 줄을 낱개로 살 수도 있따.
 * 끊어진 기타줄의 개수 N과 기타줄 브랜드 M이 주어짐
 * 각각의 브랜드에서 파는 기타줄 6개가 들어있는 패키지 가격, 낱개로 살 떄의 가격이 주어질 때, 적어도 N개를 사기 위해 필요한 최소 돈의 수를 구하라.
 * */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int result = 0;
		int minPackagePrice = Integer.MAX_VALUE, minEachPrice = Integer.MAX_VALUE;
		for(int i=0; i < M; i++) {
			minPackagePrice = Math.min(sc.nextInt(), minPackagePrice);
			minEachPrice = Math.min(sc.nextInt(), minEachPrice);
		}
		
		//가장 싼 패키지 가격이 낱개 6개 사는것보다 클 때
		if(minPackagePrice > minEachPrice * 6) minPackagePrice = minEachPrice * 6;
		result += (N / 6) * minPackagePrice;
		result += (N % 6) * minEachPrice < minPackagePrice ? (N % 6) * minEachPrice : minPackagePrice;	
		
		System.out.println(result);
		sc.close();
	}
}