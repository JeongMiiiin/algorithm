class Solution {
    public long solution(String expression) {
		
		long ans = 0;
		/*
		 * 1. * > + > -
		 * 2. * > - > +
		 * 3. + > - > *
		 * 4. + > * > -
		 * 5. - > * > +
		 * 6. - > + > *
		*/
		String[] priority = {"\\*", "\\+", "\\-"};
		ans = Math.max(ans, find(expression, priority));
		priority[1] = "\\-";
		priority[2] = "\\+";
		ans = Math.max(ans, find(expression, priority));
		priority[0] = "\\+";
		priority[2] = "\\*";
		ans = Math.max(ans, find(expression, priority));
		priority[1] = "\\*";
		priority[2] = "\\-";
		ans = Math.max(ans, find(expression, priority));
		priority[0] = "\\-";
		priority[2] = "\\+";
		ans = Math.max(ans, find(expression, priority));
		priority[1] = "\\+";
		priority[2] = "\\*";
		ans = Math.max(ans, find(expression, priority));
        
        return ans;
    }
    
    public static long find(String expression, String[] priority) {
		String[] firstValueList, secondValueList, thirdValueList;
		long[] firstCalcList, secondCalcList;
		
		firstValueList = expression.split(priority[0]);
		firstCalcList = new long[firstValueList.length];
		for(int i=0; i < firstValueList.length; i++) {
			secondValueList = firstValueList[i].split(priority[1]);
			secondCalcList = new long[secondValueList.length];
			for(int j=0; j < secondValueList.length; j++) {
				thirdValueList = secondValueList[j].split(priority[2]);
				secondCalcList[j] = Long.parseLong(thirdValueList[0]);
				for(int z=1; z < thirdValueList.length; z++) {
					secondCalcList[j] = calc(secondCalcList[j], Long.parseLong(thirdValueList[z]), priority[2]);
				}
			}
			firstCalcList[i] = secondCalcList[0];
			for(int z=1; z < secondCalcList.length; z++) {
				firstCalcList[i] = calc(firstCalcList[i], secondCalcList[z], priority[1]);
			}
		}
		
		long result = firstCalcList[0];
		for(int z=1; z < firstCalcList.length; z++) {
			result = calc(result, firstCalcList[z], priority[0]);
		}
		
		return Math.abs(result);
	}
	
	public static long calc(long n1, long n2, String operator) {
		switch(operator) {
			case "\\*" : return (n1 * n2); //곱셈
			case "\\+" : return (n1 + n2); //덧셈
			default : return (n1 - n2); //뺄셈
		}
	}
}