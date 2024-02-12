import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class Student implements Comparable<Student> {
		String name;
		int cnt;
		public Student(String name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Student o) {
			// TODO Auto-generated method stub
			return o.cnt != this.cnt ? o.cnt - this.cnt : this.name.compareTo(o.name);
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Map<String, Integer> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		for(int i=0; i < N; i++) map.put(st.nextToken(), 0);
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(sc.nextLine(), " ");
			while(st.hasMoreTokens()) {
				String target = st.nextToken();
				map.put(target, map.getOrDefault(target, 0) + 1);
			}
		}
		PriorityQueue<Student> pq = new PriorityQueue<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()) pq.add(new Student(entry.getKey(), entry.getValue()));
		
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			Student cur = pq.poll();
			sb.append(cur.name + " " + cur.cnt + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}