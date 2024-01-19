import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Candidate implements Comparable<Candidate>{
		int fileScore, interviewScore;
		public Candidate(int fileScore, int interviewScore) {
			this.fileScore = fileScore;
			this.interviewScore = interviewScore;
		}
		@Override
		public int compareTo(Candidate o) {
			// TODO Auto-generated method stub
			return this.fileScore - o.fileScore;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			Candidate[] arr = new Candidate[N];
			for(int i=0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				arr[i] = new Candidate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(arr); //서류 순위로 정렬
			int result = 0, prev = N + 1;
			for(int i=0; i < N; i++) {
				if(arr[i].interviewScore < prev) { //인터뷰 순위가 앞서는 경우
					result++;
					prev = arr[i].interviewScore;
				}
			}
			sb.append(result + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}