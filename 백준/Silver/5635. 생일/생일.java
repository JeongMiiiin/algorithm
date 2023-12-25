import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Student implements Comparable<Student>{
		String name;
		int day, month, year;
		public Student(String name, int day, int month, int year) {
			this.name = name;
			this.day = day;
			this.month = month;
			this.year = year;
		}
		@Override
		public int compareTo(Student o) {
			if(this.year != o.year) return o.year - this.year;
			else if(this.month != o.month) return o.month - this.month;
			else return o.day - this.day;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//학생 세팅
		PriorityQueue<Student> pq = new PriorityQueue<>();
		for(int i=0; i < N; i++) pq.add(new Student(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
		
		String youngName = pq.poll().name; //가장 나이가 적은 사람
		String oldName = youngName;
		 
		while(pq.size() > 1) pq.poll();
		if(pq.size() == 1) oldName = pq.poll().name;
		
		System.out.println(youngName);
		System.out.println(oldName);
		sc.close();
	}
}