class Solution {
    public String reverseWords(String s) {
        String ans = "";
        String str = "";

        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == ' ' && !str.equals("")){
                ans = str + " " + ans;
                str = "";
            }else if(s.charAt(i) == ' ') continue;
            else
            str += s.charAt(i);
        }
        if(!str.equals(""))
        ans = str + " " + ans;

        return ans.trim(); 
    }
}