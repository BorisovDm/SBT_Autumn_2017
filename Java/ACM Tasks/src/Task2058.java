import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Task2058 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        Set<String> set = new HashSet<>();

        for (int length = 1; length <= str.length(); length++) {
            for (int i = 0; i <= str.length() - length; i++) {
                set.add(str.substring(i, i + length));
            }
        }
        System.out.println(set.size());
    }
}
