import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Truck {
		int weight, position = 0;
		public Truck(int weight) {
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		Truck[] trucks = new Truck[n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < n; i++) trucks[i] = new Truck(Integer.parseInt(st.nextToken()));
		int untilTime = 0, nextIdx = 0;
		Queue<Truck> q = new LinkedList<>();
		while(nextIdx < n) {
			int total = 0, size = q.size();
			Truck cur;
			for(int i=0; i < size; i++) {
				cur = q.poll();
				if(++cur.position < w) {
					total += cur.weight;
					q.add(cur);
				}
			}
			if(total + trucks[nextIdx].weight <= L) q.add(trucks[nextIdx++]);
			untilTime++;
		}
		
		System.out.println(untilTime + w);
		br.close();
	}
}