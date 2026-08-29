import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : strs) {

            // Convert string to char array
            char[] ch = word.toCharArray();

            // Sort characters
            Arrays.sort(ch);

            // Convert back to string
            String key = new String(ch);

            // If key is not present, create new list
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            // Add original word to that key
            map.get(key).add(word);
        }

        // Return all grouped anagrams
        return new ArrayList<>(map.values());
    }
}