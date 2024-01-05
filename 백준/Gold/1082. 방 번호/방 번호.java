import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 백준 1082 - 방 번호
 * 방 번호를 정하려면 1층 문방구에서 파는 숫자를 구매해야 함
 * 
 * */

public class Main {
	static class Num implements Comparable<Num>{
		int idx, weight;
		public Num(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		@Override
		public int compareTo(Num o) {
			return o.idx - this.idx;
		}
	}
	
	static class Value implements Comparable<Value> {
		String s;
		
		public Value(String s) {
			this.s = s;
		}
		@Override
		public int compareTo(Value o) {
			// TODO Auto-generated method stub
			return o.s.length() - this.s.length();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Num[] arr = new Num[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < N; i++) arr[i] = new Num(i, Integer.parseInt(st.nextToken()));
		Arrays.sort(arr);
		int K = Integer.parseInt(br.readLine());
		TreeSet<Value>[] dp = new TreeSet[K + 1];
		for(int i=0; i <= K; i++) dp[i] = new TreeSet<Value>();
		
		for(int i=1; i <= K; i++) {
			for(Num info : arr) {
				if(info.weight > i) continue; //넣을 수 없는
				for(Value val : dp[i - info.weight]) dp[i].add(new Value(info.idx + val.s));
				dp[i].add(new Value(String.valueOf(info.idx)));
			}
		}
		
		String result = "";
		for(Value val : dp[K]) {
			result = val.s;
			if(result.charAt(0) == '0') dp[K].add(new Value(result.substring(1, result.length())));
			else break;
		}
		
		System.out.println(result);
		br.close();
	}
}