import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

/*
 * 프로그래머스 - 주차 요금 계산
 * 
 * 주차장의 요금표와 입차, 출차 기록이 주어졌을 때
 * 차량별로 주차요금을 계산하려고 함.
 * 
 * 출차기록이 없을 때에는 23:59에 나간것으로 간주
 * 누적 주차 시간을 계산하여 요금 정산해야함
 * 누적 주차 시간이 기본시간 이하라면, 기본요금 청구
 * 기본시간을 초과하면, 기본 요금에 더하여 단위 시간마다 단위 요금을 청구
 * 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림한다.
 * 차량번호가 작은 자동차부터 청구할 주차요금을 정수배열에 담아서 return
 * 
 * 만들어야할 값
 * 1. 차량를 관리할 클래스
 * 2. 기본시간, 기본요금, 단위시간, 단위요금
*/

class Solution {
    static class ParkingCar implements Comparable<ParkingCar>{
		int inTime, outTime;
		int accumulateTime;
		String carNum;
		boolean inStatus = true;
		int accumulateFees;
		
		public ParkingCar(String carNum) {
			this.carNum = carNum;
		}
		
		//출차처리
		public void outCar() {
			accumulateTime += this.outTime - this.inTime;
			//주차상태 변경
			this.inStatus = false;
		}
		public void calcFees() {
			int fees = 0;
			//기본시간에 해당하는 경우 기본 요금
			if(this.accumulateTime <= basicTime) fees = basicFees;
			else {
				//초과한 시간만큼 계산해서 리턴
				int overTime = (this.accumulateTime - basicTime) % unitTime > 0 ? ((this.accumulateTime - basicTime) / unitTime) + 1 : (this.accumulateTime - basicTime) / unitTime;
				fees = basicFees + (overTime * unitFees);
			}
			
			this.accumulateFees = fees;
		}
		@Override
		public int compareTo(ParkingCar o) {
			// TODO Auto-generated method stub
			return Integer.parseInt(this.carNum) - Integer.parseInt(o.carNum);
		}
	}
    
    static int basicTime, basicFees, unitTime, unitFees;
	static Map<String, ParkingCar> carList = new HashMap<>();
    public int[] solution(int[] fees, String[] records) {
        //리스트 초기화
		carList.clear();
		
		//요금 계산을 위한 세팅
		basicTime = fees[0];
		basicFees = fees[1];
		unitTime = fees[2];
		unitFees = fees[3];
		
		String[] info = new String[3];
		
		for(String s : records) {
			info = s.split(" ");
			int infoTime = changeMinutes(info[0]);
			String carNum = info[1];
			String status = info[2];
			if(status.equals("IN")) {
				//입차인 경우
				//오늘 처음 들어온 차인 경우 carList에 새로 생성
				if(carList.get(carNum) == null) carList.put(carNum, new ParkingCar(carNum));
				carList.get(carNum).inTime = infoTime;
				carList.get(carNum).inStatus = true;
			} else {
				//출차인 경우
				ParkingCar p = carList.get(carNum);
				p.outTime = infoTime;
				p.outCar();
			}
		}
		
		TreeSet<ParkingCar> cars = new TreeSet<>();
		int endTime = changeMinutes("23:59");
		for(Entry<String, ParkingCar> pair : carList.entrySet()) {
			ParkingCar cur = pair.getValue();
			//아직 출차하지 않은 차인 경우
			if(cur.inStatus) {
				cur.outTime = endTime;
				cur.outCar();
			}
			cur.calcFees();
			cars.add(cur);
		}
		
        int[] answer = new int[carList.size()];
        Iterator<ParkingCar> iter = cars.iterator();
        int idx = 0;
        while(iter.hasNext()) answer[idx++] = iter.next().accumulateFees; 
        return answer;
    }
    
    //주어진 시간 문자열을 분단위로 만들어서 돌려주는 함수
	private static int changeMinutes(String time) {
		String[] timeInfo = time.split(":");
		int hours = Integer.parseInt(timeInfo[0]) * 60;
		int minutes = Integer.parseInt(timeInfo[1]);
		return hours + minutes;
	}
}