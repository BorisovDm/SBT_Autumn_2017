import java.util.HashMap;
import java.util.Scanner;

public class Task2055 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long x = in.nextLong();
        long y = in.nextLong();
        long m = in.nextLong();

        HashMap<Long, Long> dict = new HashMap<>();
        Long i = 1L;
        dict.put(a, i);

        while(true){
            i++;
            a = (x * a + y) % m;
            if(dict.containsKey(a)){
                Long previousIndex = dict.get(a);
                System.out.println((i - previousIndex) + " " + (previousIndex - 1));
                break;
            }
            dict.put(a, i);
        }
    }
}
