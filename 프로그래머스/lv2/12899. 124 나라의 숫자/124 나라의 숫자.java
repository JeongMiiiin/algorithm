/*
11 -> 42
12 -> 44
13 -> 111
*/

class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();

        while (n > 0) {
            int num = n % 3;
            if (num == 0) {
                answer.insert(0, "4"); 
                n = n / 3 - 1;
            } else {
                answer.insert(0, Integer.toString(num));
                n = n / 3;
            }

        }

        return answer.toString();
    }
}