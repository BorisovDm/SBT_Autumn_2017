import java.util.*;

public class Task2051 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        Scanner in = new Scanner(System.in);
        char[] str = (in.nextLine()).toCharArray();

        for (int i = 0; i < str.length; i++) {
            if(str[i] == '('){
                list.add(i + 1);
            }
            else{
                treeMap.put(list.get(list.size() - 1), i + 1);
                list.remove(list.size() - 1);
            }
        }
        for (Map.Entry<Integer, Integer> item: treeMap.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue());
        }
    }
}
