import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Task2054 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfSets = Integer.parseInt(in.nextLine());
        StringBuilder output = new StringBuilder();

        HashMap<Integer, TreeSet> dict = new HashMap<>();
        Set<Integer> resultSet = new TreeSet<>();

        for (int i = 0; i < numberOfSets; i++) {
            String[] numbers = in.nextLine().split(" ");
            for (int j = 1; j <= Integer.parseInt(numbers[0]); j++) {
                int number = Integer.parseInt(numbers[j]);
                resultSet.add(number);
                if (dict.containsKey(number)){
                    TreeSet<Integer> set = dict.get(number);
                    set.add(i + 1);
                }
                else{
                    TreeSet<Integer> set = new TreeSet<>();
                    set.add(i + 1);
                    dict.put(number, set);
                }
            }
        }

        for(Integer number: resultSet){
            output.append(Integer.toString(number)).append(" ");
            for(Object x: dict.get(number)) output.append((Integer)x).append(" ");
            output.append("\n");
        }
        System.out.print(output);
    }
}
