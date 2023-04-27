import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
		//값 자르기
		String[] list = s.split("\\}\\,\\{");
		
		
		
		List<Integer> tempAns = new ArrayList<>();
		
		for(int i=0; i < list.length; i++) {
			for(int j=0; j < list.length; j++) {
				String target = list[j];
				String[] temp = target.split(",");
				if(temp.length > 1) {
					//앞에가 다 채워져 있을 때
					if(tempAns.size() == temp.length - 1) {
						int remain = 0;
						for(int z=0; z < temp.length; z++)
							if(!tempAns.contains(Integer.parseInt(temp[z]))) remain = Integer.parseInt(temp[z]); 
						tempAns.add(remain);
					}
				}
				//하나만 있으면 앞에 채우기
				else if(tempAns.size() == 0) tempAns.add(Integer.parseInt(temp[0]));
				
			}
			
			//마지막까지 다 채워진 경우
			if(tempAns.size() == list.length) break;
		}
		
		int[] ans = new int[tempAns.size()];
		for(int i=0; i < ans.length; i++) ans[i] = tempAns.get(i);
        
        return ans;
    }
}