import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 17266 - 어두운 굴다리
 * 굴다리 모든 길 0 ~ N을 밝히게 가로등을 설치해달라고 부탁.
 * 인천광역시에서 가로등을 설치할 개수 M과 각 가로등의 위치 x들의 결정을 끝냄.
 * 각 가로등은 높이만큼 주위를 비출 수 있따.
 * 최소한의 높이로 굴다리 모든길 0 ~ N을 밝히고자 한다.
 * 단 가로등은 모두 높이가 같아야 하고, 정수이다.
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//가로등 위치 세팅
		int[] arr = new int[M];
		for(int i=0; i < M; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int result = 0, left = 1, right = N;
		while(left <= right) {
			int mid = (left + right) / 2;
			boolean flag = true;
			
			int prev = 0;
			for(int i=0; i < M; i++) {
				if(arr[i] - mid <= prev) {
					prev = arr[i] + mid;
				} else {
					flag = false;
					break;
				}
			}
			
			if(flag && N - prev > 0) flag = false;
			
			if(flag) {
				result = mid;
				right = mid - 1;
			} else left = mid + 1;
		}
		
		System.out.println(result > 0 ? result : right);
		br.close();
	}
}