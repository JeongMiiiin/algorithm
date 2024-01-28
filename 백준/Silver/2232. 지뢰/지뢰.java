import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int max = 0;
		for(int i=0; i < N; i++) {
			arr[i] = sc.nextInt();
			max = Math.max(max, arr[i]);
		}
		boolean[] boomed = new boolean[N];
		List<Integer> result = new ArrayList<>();
		for(int i = max; i > 0; i--) {
			for(int j=0; j < N; j++) {
				if(boomed[j] || arr[j] < i) continue;
				boomed[j] = true;
				result.add(j + 1);
				Queue<Integer> q = new LinkedList<>();
				q.add(j);
				while(!q.isEmpty()) {
					int cur = q.poll();
					if(cur - 1 >= 0 && !boomed[cur - 1] && arr[cur - 1] < arr[cur]) {
						boomed[cur - 1] = true;
						q.add(cur - 1);
					}
					if(cur + 1 < N && !boomed[cur + 1] && arr[cur + 1] < arr[cur]) {
						boomed[cur + 1] = true;
						q.add(cur + 1);
					}
				}
			}
			
		}
		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		for(int num : result) sb.append(num + "\n");
		
		System.out.println(sb.toString());
		sc.close();
	}
}