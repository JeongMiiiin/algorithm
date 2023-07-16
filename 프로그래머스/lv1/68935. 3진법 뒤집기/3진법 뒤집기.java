class Solution {
    public int solution(int n) {
        String s = Integer.toString(n, 3);
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) sb.insert(0, c);
        return Integer.parseInt(sb.toString(), 3);
    }
}