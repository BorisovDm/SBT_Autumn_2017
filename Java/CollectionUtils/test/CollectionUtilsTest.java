import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CollectionUtilsTest {
    @Test
    public void testAddAll(){
        List<Integer> list1 = new ArrayList<>();
        List<Number> list2 = new ArrayList<>();
        for (int i = 0; i < 6; i++) list1.add(i);
        for (int i = 6; i < 10; i++) list2.add(i * 1.);

        CollectionUtils.addAll(list1, list2);
        assertEquals(10, list2.size());
    }

    @Test
    public void testNewArrayList(){
        List<Integer> list = CollectionUtils.newArrayList();
        int value = 5;
        list.add(value);

        assertEquals(1, list.size());
        assertEquals(new Integer(value), list.get(0));
    }

    @Test
    public void testIndexOf(){
        List<Number> list = new ArrayList<>();

        int size = 6;
        for (int i = 0; i < size; i++) list.add(i);
        for (int i = 0; i < size; i++) {
            assertEquals(i, CollectionUtils.indexOf(list, i));
        }

        Double numberValue = 3.4;
        list.add(numberValue);
        assertEquals(size, CollectionUtils.indexOf(list, numberValue));
    }

    @Test
    public void testLimit() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) list.add(i);
        assertEquals(3, CollectionUtils.limit(list, 3).size());
    }

    @Test
    public void testAdd(){
        List<Number> list = new ArrayList<>();
        CollectionUtils.add(list, 1);
        CollectionUtils.add(list, 1.2);
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveAll(){
        List<Number> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        CollectionUtils.add(list1, 1.1);
        for (int i = 0; i < 5; i++) {
            CollectionUtils.add(list1, i);
        }

        for (int i = 0; i < 3; i++) {
            CollectionUtils.add(list2, i);
        }

        CollectionUtils.removeAll(list1, list2);
        assertEquals(3, list1.size());
    }

    @Test
    public void testContainsAll(){
        List<Number> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        CollectionUtils.add(list1, 1.1);
        for (int i = 0; i < 5; i++) {
            CollectionUtils.add(list1, i);
        }

        for (int i = 0; i < 3; i++) {
            CollectionUtils.add(list2, i);
        }
        assertEquals(true, CollectionUtils.containsAll(list1, list2));
    }

    @Test
    public void testContainsAnyTrue(){
        List<Number> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        CollectionUtils.add(list2, 3);
        CollectionUtils.add(list1, 1.1);
        for (int i = 0; i < 5; i++) {
            CollectionUtils.add(list1, i);
        }
        assertEquals(true, CollectionUtils.containsAny(list1, list2));
    }

    @Test
    public void testContainsAnyFalse(){
        List<Number> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        CollectionUtils.add(list2, 6);
        CollectionUtils.add(list1, 1.1);
        for (int i = 0; i < 5; i++) {
            CollectionUtils.add(list1, i);
        }
        assertEquals(false, CollectionUtils.containsAny(list1, list2));
    }

    @Test
    public void testRangeWithoutComparator(){
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,2,3,3,4));
        list = CollectionUtils.range(list, 2, 4);

        Integer[] expectedArray = {2,2,3,3,3,4,4};
        assertArrayEquals(expectedArray, list.toArray());
    }

    @Test
    public void testRangeWithComparator(){
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,2,3,3,4));
        list = CollectionUtils.range(list, 2, 4, new Comparator<Integer>() {
            @Override
            public int compare(Integer v1, Integer v2) {
                return v1 < v2 ? -1 : v1 > v2 ? +1 : 0;
            }
        });
        Integer[] expectedArray = {2,2,3,3,3,4,4};
        assertArrayEquals(expectedArray, list.toArray());
    }
}