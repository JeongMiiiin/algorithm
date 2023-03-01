import java.util.Scanner;

//최소공배수, 최대공약수
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		int cnt = 1, max = 2;
		
		//최대공약수 찾기
		while(true) {
			if(cnt > A || cnt > B) break; //증가하는 수가 두 수중 어느 한 쪽이라도 일치할 시 반복문 중단
			if(A % cnt == 0 && B % cnt == 0) max = cnt; //나머지가 없을 때 공약수라 판단 후 max에 할당 (cnt는 계속 증가하기에 바로 할당)
			cnt++;
		}
		
		//최소공배수는 한쪽값에서 최대공약수를 나누고, 다른 값을 곱한 값이기에
		int min = (A / max) * B;
		
		System.out.println(max);
		System.out.println(min);
		
	}
}
