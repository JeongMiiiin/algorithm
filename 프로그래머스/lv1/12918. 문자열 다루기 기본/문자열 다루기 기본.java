class Solution {
    public boolean solution(String s) throws Exception {
        try {
            int test = Integer.parseInt(s);
            return s.length() == 4 || s.length() == 6;
        } catch (Exception e){
            return false;
        }
    }
}