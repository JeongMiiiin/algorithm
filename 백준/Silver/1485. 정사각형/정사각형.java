import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static class Point {
		int x; 
		int y;
		
		public Point (int x, int y) {
			this.x = x; 
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int t=1; t <= T; t++) {
			ArrayList<Point> pointList = new ArrayList<>();
			for (int j=0; j < 4; j++) pointList.add(new Point(sc.nextInt(), sc.nextInt()));
			

			double[] len = new double[6];
			int count=0;
			for (int j=0; j<3; j++) {
				for (int k=j+1; k < 4; k++) {
					if (j==0 && k==1) len[count++] = calculateDistance(pointList.get(j), pointList.get(k));
					else len[count++] = calculateDistance(pointList.get(j), pointList.get(k));
				}
			}
			boolean isSquare = true;
			
			//정사각형이면 변의 길이를 sort했을때 앞에 4개의 값은 4개의 변이 되고
			//뒤의 2개의 값은 대각선이 될 것이다. 
			Arrays.sort(len);
			// 4개의 변중 하나라도 다르면 정사각형 X
			for (int j=1; j<4; j++) {
				if (len[0] != len[j]) isSquare = false;
			}
			// 대각선의 길이가 같지 않으면 정사각형 X
			if (len[4]!=len[5]) isSquare = false;
			
			int result = 0;
			if (isSquare) result++;
			sb.append(result + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
	static double calculateDistance(Point point1, Point point2) {
		int dx = point1.x - point2.x;
		int dy = point1.y - point2.y;
		return Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
	}
}