import java.util.*;

public class Task2056 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String, Integer> dict = new HashMap<>();
        Set<String> wordSet = new TreeSet<>();

        while(in.hasNext()){
            String[] words = in.nextLine().split(" ");
            for(String word: words) {
                if(word.length() == 0) continue;
                String lowerCaseWord = word.toLowerCase();
                if (dict.containsKey(lowerCaseWord)) {
                    int oldValue = dict.get(lowerCaseWord);
                    dict.put(lowerCaseWord, oldValue + 1);
                }
                else dict.put(lowerCaseWord, 1);
            }
        }

        int maxValue = 0;
        for (int value : dict.values())
            if(value > maxValue) maxValue = value;

        for (Map.Entry entry : dict.entrySet()) {
            if((int)entry.getValue() == maxValue) {
                wordSet.add((String)entry.getKey());
            }
        }

        for(String st: wordSet)
            System.out.println(st);
    }
}