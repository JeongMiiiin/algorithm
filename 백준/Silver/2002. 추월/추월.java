import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Queue<String> in = new LinkedList<>();
		for(int i=0; i < N; i++) in.add(sc.nextLine());
		Queue<String> out = new LinkedList<>();
		for(int i=0; i < N; i++) out.add(sc.nextLine());
		List<String> garbage = new ArrayList<>();
		int result = 0;
		while(!out.isEmpty()) {
			String outCar = out.poll();
			while(garbage.contains(in.peek())) in.poll();
			if(outCar.equals(in.peek())) in.poll();
			else result++;
			garbage.add(outCar);
		}
		System.out.println(result);
		sc.close();
	}
}