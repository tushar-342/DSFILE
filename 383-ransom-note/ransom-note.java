class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int n = ransomNote.length();
        int m = magazine.length();
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> have = new HashMap<>();
        for(int i=0; i<n; i++){
            char ch = ransomNote.charAt(i);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        for(int i=0; i<m; i++){
            char ch = magazine.charAt(i);
            have.put(ch, have.getOrDefault(ch, 0) + 1);
        }
        for(char c : need.keySet()){
            int fneed = need.get(c);
            int fhave = have.getOrDefault(c, 0);
            if(fhave < fneed){
                return false;
            }
        }
        return true;
    }
}
