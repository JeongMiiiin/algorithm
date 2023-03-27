import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static class ArrayQueue {
		int front, rear, size;
		int [] queue;
		public ArrayQueue(int size) {
			this.front = this.rear = -1;
			this.size = size;
			this.queue = new int[size];
		}
		public boolean isEmpty() {
			return front == rear;
		}
		public boolean isFull() {
			return rear == size - 1;
		}
		public int size() {
			return rear - front;
		}
		public void offer(int value) {
			if(isFull()){
				System.out.println("Queue is Full");
				return;
			}
			queue[++rear] = value;
		}
		public int poll() {
			return queue[++front];
		}
		public int front() {
			return queue[front + 1];
		}
		public int back() {
			return queue[rear];
		}
		public void clear() {
			front = rear = -1;
			queue = new int[size];
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		ArrayQueue q = new ArrayQueue(n);
		String cmd;
		int target;
		for(int i=0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cmd = st.nextToken();
			switch(cmd) {
				case "push":
					target = Integer.parseInt(st.nextToken());
					q.offer(target);
					break;
				case "pop":
					if( !q.isEmpty() ) bw.write(q.poll() + "\n");
					else bw.write(-1 + "\n");
					break;
				case "size":
					bw.write(q.size() + "\n");
					break;
				case "empty":
					if(q.isEmpty()) bw.write(1 + "\n");
					else bw.write(0 + "\n");
					break;
				case "front":
					if( !q.isEmpty() ) bw.write(q.front() + "\n");
					else bw.write(-1 + "\n");
					break;
				case "back":
					if( !q.isEmpty() ) bw.write(q.back() + "\n");
					else bw.write(-1 + "\n");
					break;
			}
		}
		
		bw.close();
	}
}