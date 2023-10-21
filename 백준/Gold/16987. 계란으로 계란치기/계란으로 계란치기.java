/*
 * 백준 16987 - 계란으로 계란치기
 * 각 계란에는 내구도와 무게가 정해져있음.
 * 계란으로 계란을 치게 되면
 * 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 됨.
 * 내구도가 0 이하가 되는 순간 깨지게 됨.
 * 계란에 대해 왼쪽부터 차례로 들어서
 * 한 번씩만 다른 계란을 쳐 최대한 많은 계란을 깨는 문제
 * 1. 가장 왼쪽의 계란을 든다.
 * 2. 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 침
 * 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어감.
 * 3. 가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에 들고 2번 과정을 다시 진행.
 * 단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 과정 종료
 * 
 * 최대 몇 개의 계란을 깰 수 있는지 알아맞춰보자.
 * 
 * 첫째 줄 N -> 계란의 수
 * 두번째부터 N개의 줄 -> 각 계란의 내구도와 무게 정보
 * 
 * DFS로 순회하면서 현재 최대값보다 남은게 적으면 바로 종료
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Egg {
		int health, weight;
		public Egg(int health, int weight) {
			this.health = health;
			this.weight = weight;
		}
	}
	
	static int N, result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//초기 세팅
		N = Integer.parseInt(br.readLine());
		Egg[] eggList = new Egg[N];
		boolean[] crashed = new boolean[N];
		result = 0;
		
		//계란 목록 세팅
		StringTokenizer st;
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int health = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			eggList[i] = new Egg(health, weight);
		}
		
		find(0, 0, eggList, crashed);
		
		bw.write(String.valueOf(result));
		bw.close();
		br.close();
	}
	
	static void find(int curIdx, int cnt, Egg[] eggList, boolean[] crashed) {
		//최근에 든 계란이 가장 오른쪽 계란인 경우
		if(curIdx == N) {
			result = Math.max(result, cnt);
			return;
		}
		
		//이미 깨진 계란인 경우
		if(crashed[curIdx]) {
			find(curIdx + 1, cnt, eggList, crashed);
			return;
		}
		
		boolean flag = false;
		//각 값을 보내기
		for(int i=0; i < N; i++) {
			//자기 자신이거나 이미 깨진 계란인 경우 패스
			if(i == curIdx || crashed[i]) continue;
			
			flag = true;
			int tempCnt = cnt;
			//배열 복사
			Egg[] tempEggList = new Egg[N];
			for(int j=0; j < N; j++) tempEggList[j] = new Egg(eggList[j].health, eggList[j].weight);
			boolean[] tempCrashed = Arrays.copyOf(crashed, N);
			Egg curEgg = tempEggList[curIdx], compareEgg = tempEggList[i];
			curEgg.health -= compareEgg.weight;
			compareEgg.health -= curEgg.weight;
			if(curEgg.health <= 0) {
				tempCnt++;
				tempCrashed[curIdx] = true;
			}
			if(compareEgg.health <= 0) {
				tempCnt++;
				tempCrashed[i] = true;
			}
			
			find(curIdx + 1, tempCnt, tempEggList, tempCrashed);
		}
		if(!flag) find(curIdx + 1, cnt, eggList, crashed);
	}
}