import java.util.Scanner;
import java.util.TreeSet;

public class Task2059 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TreeSet<Integer> numberSet = new TreeSet<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int number = in.nextInt();
            Integer searchResult = numberSet.higher(number);
            output.append(searchResult == null ? -1 : searchResult)
                    .append(" ");
            numberSet.add(number);
        }

        System.out.println(output);
    }
}
