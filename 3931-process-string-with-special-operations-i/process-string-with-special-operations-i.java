class Solution {
    public String processStr(String s) {
        int n = s.length();
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            
            if(Character.isLowerCase(ch)){
                res.append(ch);
            }else if(ch == '#'){
                res.append(res.toString());
            }else if(ch == '%'){
                res.reverse();
            }else if(ch == '*'){
                if(res.length() > 0){
                    res.deleteCharAt(res.length() - 1);
                }
            }
        }
        return res.toString();
            
    }
}