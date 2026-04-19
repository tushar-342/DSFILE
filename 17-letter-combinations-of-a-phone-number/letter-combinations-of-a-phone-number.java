import java.util.*;
class Solution {
    HashMap<Character,String>f= new  HashMap<>();
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length()==0) return res;
        f.put('2', "abc");
        f.put('3', "def");
        f.put('4', "ghi");
        f.put('5', "jkl");
        f.put('6', "mno");
        f.put('7', "pqrs");
        f.put('8', "tuv");
        f.put('9', "wxyz");

        int n = digits.length();
        int idx=0;

        fun(digits,n,idx,new StringBuilder(),res);
        return res;
    }
    void fun(String digits, int n, int idx, StringBuilder diary, List<String>res){
        if(idx==n){
            res.add(diary.toString());
            return;
        }
            String choice = f.get(digits.charAt(idx));
            for(int j=0;j<choice.length();j++){
                diary.append(choice.charAt(j));
                fun(digits,n,idx+1,diary,res);
                //BACKTRACK
                diary.deleteCharAt(diary.length()-1);
        }
    }
}