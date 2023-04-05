import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 백준 15961 - 회전초밥
 * 
 * 주어지는 값
 * N : 벨트에 있는 접시의 수
 * d : 초밥의 가짓수
 * k : 연속해서 먹는 접시의 수
 * c : 쿠폰 번호
 * 두번째 줄 ~ N번째 줄 : 벨트에 올라가 있는 초밥의 종류
 * 
 * 회전초밥 음식점에는 회전하는 벨트 위에 여러 가지 종류의 초밥이 접시에 담겨있음.
 * 벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있다.
 * 
 * 두 가지 행사를 통해 매상을 올리고자 한다.
 * 1. 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
 * 2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행.
 *    1번 행사에 참여할 경우, 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료 제공
 *    만약 해당 번호가 벨트 위에 없을 경우, 요리사가 만들어 손님에게 제공
 * 회전초밥 음식점의 벨트 상태, 메뉴에 있는 초밥의 가짓수, 연속해서 먹는 접시의 개수
 * 쿠폰번호가 주어졌을 때, 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하라
 * 
 * LinkedSet로 
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] useList = new int[d + 1]; //현재 담고있는 가짓수 체크
		//벨트세팅
		int[] belt = new int[N];
		for(int i=0; i < N; i++) belt[i] = Integer.parseInt(br.readLine());
		
		br.close();
		
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> diff = new HashSet<>();
		
		//처음 시작
		for(int i=0; i < k; i++) {
			int target = belt[i];
			q.offer(target);
			useList[target]++;
			diff.add(target);
		}
		
		//쿠폰으로 먹을 수 있는 가짓수 담기
		diff.add(c);
		useList[c]++;
		int ans = diff.size(); //처음 값 세팅
		
		for(int i=k; i < N; i++) {
			int first = q.poll();
			//유일한 가짓수였으면 set에서 제거
			if(--useList[first] == 0) diff.remove(first); 
			int target = belt[i];
			q.offer(target);
			useList[target]++;
			diff.add(target);
			ans = Math.max(ans, diff.size());
		}
		//마지막 뒤에 돌려보기
		for(int i=0; i < k - 1; i++) {
			int first = q.poll();
			//유일한 가짓수였으면 set에서 제거
			if(--useList[first] == 0) diff.remove(first);
			int target = belt[i];
			q.offer(target);
			useList[target]++;
			diff.add(target);
			ans = Math.max(ans, diff.size());
		}
		
		//출력
		System.out.println(ans);
	}
}