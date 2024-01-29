import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Word implements Comparable<Word>{
		String s;
		int count;
		public Word(String s, int count) {
			this.s = s;
			this.count = count;
		}
		@Override
		public int compareTo(Word o) {
			if(this.count != o.count) return o.count - this.count;
			if(this.s.length() != o.s.length()) return o.s.length() - this.s.length();
			else return this.s.compareTo(o.s);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();
		for(int i=0; i < N; i++) {
			String s = br.readLine();
			if(s.length() >= M) map.put(s, map.getOrDefault(s, 0) + 1);
		}
		List<Word> list = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			list.add(new Word(entry.getKey(), entry.getValue()));
		}
		
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(Word w : list) sb.append(w.s + "\n");
		System.out.println(sb.toString());
		br.close();
	}
}