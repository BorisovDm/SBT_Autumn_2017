import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Task2052 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) list.add(in.nextInt());

        for (int i = 0; i < k; i++) {
            int number = in.nextInt();

            if(number == 1){
                list.clear();
                break;
            }
            else{
                ListIterator<Integer> iterator = (ListIterator<Integer>) list.iterator();
                int delNumber = number, j = 0;
                while(iterator.hasNext()){
                    iterator.next();
                    if(iterator.nextIndex() == delNumber){
                        iterator.remove();
                        j++;
                        delNumber = number * (j + 1) - j;
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Integer elem: list) builder.append(Integer.toString(elem)).append(" ");
        System.out.println(builder);
    }
}
