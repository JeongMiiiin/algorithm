import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class Score implements Comparable<Score>{
		int Korean, English, Math;
		String name;
		public Score(String name, int Korean, int English, int Math) {
			this.name = name;
			this.Korean = Korean;
			this.English = English;
			this.Math = Math;
		}
		
		@Override
		public int compareTo(Score o) {
			if(o.Korean != this.Korean) return o.Korean - this.Korean;
			if(o.English != this.English) return this.English - o.English;
			if(o.Math != this.Math) return o.Math - this.Math;
			return this.name.compareTo(o.name);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Score[] arr = new Score[N];
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			arr[i] = new Score(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < N; i++) sb.append(arr[i].name + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
}