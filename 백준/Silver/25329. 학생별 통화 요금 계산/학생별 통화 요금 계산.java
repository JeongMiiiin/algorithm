import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static class Student implements Comparable<Student>{
		String name;
		int timeVal;
		public Student(String name, int timeVal) {
			this.name = name;
			this.timeVal = timeVal;
		}
		@Override
		public int compareTo(Student o) {
			return o.timeVal != this.timeVal ? o.timeVal - this.timeVal : this.name.compareTo(o.name);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Map<String, Integer> map = new HashMap<>();
		for(int i=0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int time = changeTime(st.nextToken());
			String student = st.nextToken();
			map.put(student, map.getOrDefault(student, 0) + time);
		}
		
		List<Student> list = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()) list.add(new Student(entry.getKey(), calcFee(entry.getValue())));
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < list.size(); i++) sb.append(list.get(i).name + " " + list.get(i).timeVal + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static int changeTime(String timeVal) {
		int result = 0;
		String[] info = timeVal.split(":");
		result += Integer.parseInt(info[0]) * 60;
		result += Integer.parseInt(info[1]);
		return result;
	}
	
	private static int calcFee(int timeVal) {
		int result = 0;
		if(timeVal <= 100) result = 10;
		else {
			timeVal -= 100;
			result += 10;
			while(timeVal > 50) {
				result += 3;
				timeVal -= 50;
			}
			result += 3;
		}
		
		return result;
	}
}