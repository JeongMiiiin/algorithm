import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class Student implements Comparable<Student>{
		int num, cnt;
		
		public Student(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Student o) {
			// TODO Auto-generated method stub
			return this.cnt != o.cnt ? o.cnt - this.cnt : this.num - o.num;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[][] map = new int[N][5];
		Student[] result = new Student[N];
		for(int i=0; i < N; i++) {
			result[i] = new Student(i + 1, 0);
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			boolean[] checked = new boolean[i];
			for(int grade = 0; grade < 5; grade++) {
				int cur = Integer.parseInt(st.nextToken());
				for(int j=0; j < i; j++) {
					if(cur == map[j][grade] && !checked[j]) {
						checked[j] = true;
						result[j].cnt++;
						result[i].cnt++;
					}
				}
				map[i][grade] = cur;
			}
		}
		Arrays.sort(result);
		
		System.out.println(result[0].num);
		sc.close();
	}
}