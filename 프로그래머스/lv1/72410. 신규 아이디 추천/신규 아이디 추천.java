class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        for(int i = 1; i < 8; i++){
            answer = correctId(i, answer);
        }
        
        return answer;
    }
    
    private String correctId(int step, String target){
        String result = "";
        switch(step){
            case 1 :
                target = target.toLowerCase();
                break;
            case 2 :
                target = target.replaceAll("[^\\w\\-_.]*","");
                break;
            case 3 :
                target = target.replaceAll("\\.{2,}", ".");
                break;
            case 4 :
                target = target.replaceAll("^[.]|[.]$","");
                break;
            case 5 :
                if(target.length() == 0){
                    target = "a";
                }
                break;
            case 6 :
                if(target.length() > 15){
                    target = target.substring(0,15);
                    target = target.replaceAll("[.]$", "");
                }
                break;
            case 7 :
                if(2 >= target.length()){
                    String lastText = "";
                    if(target.length() == 1){
                        lastText = target;
                    } else {
                        lastText = target.substring(target.length() - 1);
                    }
                   
                    for(int z = target.length(); z < 3; z++){
                        target += lastText;
                    }
                }
                break;
            default :
                break;
        }
        result = target;
        return result;
    }
}