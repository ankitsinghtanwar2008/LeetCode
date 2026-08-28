class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuilder str = new StringBuilder();

        for(int i=0;i<s.length();i++){
            str.append(s.charAt(i));

            if(str.length() >= part.length()){
                int start = str.length() - part.length();

                if(str.substring(start).equals(part)){
                    str.delete(start, str.length());
                }
            }
        }
        return str.toString();
    }
}