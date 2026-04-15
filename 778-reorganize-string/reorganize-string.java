//Approach-2 (Using odd/even placement)
class Solution {
    public String reorganizeString(String s) {
        //dont know where the life will take me !!
        int len = s.length();
        int[] charFreq = new int[26];
        char[] ans = new char[len];


        char maxocc = '#';
        int maxlen = 0 ;

        for(char el : s.toCharArray())
        {
            charFreq[el-'a']++;

            if(charFreq[el-'a'] > (len+1)/2) return "";

            if(charFreq[el-'a'] > maxlen) {
                maxlen = charFreq[el-'a'];
                maxocc = el;
            }
        }
        int j = 0 ;
        while(maxlen > 0)
        {
            ans[j] = maxocc;
            j+=2;
            maxlen--;
            charFreq[maxocc - 'a']--;
        }

        for(char i = 'a' ; i <= 'z' ;i++)
        {
           
            while(charFreq[i-'a'] > 0)
            {
                if(j > len-1) j = 1;
                ans[j] = i;
                j+=2;
                charFreq[i-'a']--;
            }
        }

        return new String(ans);
    }
}
