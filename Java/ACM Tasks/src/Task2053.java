import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Task2053 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String, Integer> dict = new HashMap<>();
        HashSet<String> wordSet = new HashSet<>();
        StringBuilder output = new StringBuilder();
        int numberUniqueWords = 0;

        while(in.hasNext()){
            String[] words = in.nextLine().split(" ");
            for(String word: words) {
                if(word.length() == 0) continue;
                String lowerCaseWord = word.toLowerCase();
                if (wordSet.contains(lowerCaseWord)){
                    output.append(Integer.toString(dict.get(lowerCaseWord))).append(" ");
                }
                else{
                    wordSet.add(lowerCaseWord);
                    dict.put(lowerCaseWord, ++numberUniqueWords);
                    output.append(Integer.toString(numberUniqueWords)).append(" ");
                }
            }
        }
        System.out.println(output);
    }
}
