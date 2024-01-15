import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> cranes = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < N; i++) cranes.add(Integer.parseInt(st.nextToken()));
		int M = Integer.parseInt(br.readLine());
		List<Integer> boxes = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i < M; i++) boxes.add(Integer.parseInt(st.nextToken()));
		cranes.sort(Collections.reverseOrder());
		boxes.sort(Collections.reverseOrder());
		
		int time = 0;
		if(cranes.get(0) >= boxes.get(0)) {
			while(!boxes.isEmpty()) {
				int boxIdx = 0, craneIdx = 0;
				while(craneIdx < N) {
					if(boxIdx == boxes.size()) break;
					else if(cranes.get(craneIdx) >= boxes.get(boxIdx)) {
						boxes.remove(boxIdx);
						craneIdx++;
					} else boxIdx++;
				}
				time++;
			}
		} else time--;
		
		System.out.println(time);
		br.close();
	}
}