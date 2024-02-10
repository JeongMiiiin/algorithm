import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Message implements Comparable<Message>{
		int idx, num, cnt;
		public Message(int idx, int num, int cnt) {
			this.idx = idx;
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Message o) {
			return this.cnt != o.cnt ? o.cnt - this.cnt : this.idx - o.idx;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<Integer, Integer> IdxMap = new HashMap<>();
		Map<Integer, Integer> CntMap = new HashMap<>();
		int N = sc.nextInt();
		int C = sc.nextInt();
		for(int i=0; i < N; i++) {
			int target = sc.nextInt();
			if(IdxMap.get(target) == null) IdxMap.put(target, i);
			CntMap.put(target, CntMap.getOrDefault(target, 0) + 1);
		}
		PriorityQueue<Message> pq = new PriorityQueue<>();
		for(Map.Entry<Integer, Integer> entry : IdxMap.entrySet()) pq.add(new Message(entry.getValue(), entry.getKey(), CntMap.get(entry.getKey())));
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			Message cur = pq.poll();
			for(int i=0; i < cur.cnt; i++) sb.append(cur.num + " ");
		}
		
		System.out.println(sb.toString());
		sc.close();
	}
}