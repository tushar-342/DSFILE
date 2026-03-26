class Solution {
    public String removeDuplicates(String s) {
          int n = s.length();
          Stack<Character> st = new Stack<>();
          StringBuilder res = new StringBuilder();
          for(int i=0; i<n; i++){
            if(st.isEmpty()){
                st.push(s.charAt(i));
                continue;
            }
            if(st.peek()==s.charAt(i)){
                st.pop();
                continue;
            }else{
                st.push(s.charAt(i));
             }
          }
          while(!st.isEmpty()){
            res.append(st.pop());
          }
          return res.reverse().toString();

        
    }
}