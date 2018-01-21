import org.junit.Test;
import static org.junit.Assert.*;

public class CountMapImplTest {
    @Test
    public void testAddInteger(){
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(5);
        map.add(5);
        map.add(7);
        map.add(9);
        map.add(9);
        map.add(9);

        assertEquals(2, map.getCount(5));
        assertEquals(0, map.getCount(6));
        assertEquals(1, map.getCount(7));
        assertEquals(0, map.getCount(8));
        assertEquals(3, map.getCount(9));
    }

    @Test
    public void testRemoveInteger(){
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(5);
        map.add(5);
        map.add(7);

        assertEquals(2, map.remove(5));
        assertEquals(0, map.remove(6));

        assertEquals(0, map.getCount(5));
        assertEquals(0, map.getCount(6));
    }

    @Test
    public void testSizeInteger(){
        CountMap<Integer> map = new CountMapImpl<>();
        int size = 10;
        for (int i = 0; i <size ; i++) {
            map.add(i);
        }
        assertEquals(size, map.size());
    }

    @Test
    public void testAddAllInteger(){
        CountMap<Integer> map1 = new CountMapImpl<>();
        CountMap<Integer> map2 = new CountMapImpl<>();

        int size = 10;
        for (int i = 0; i <size ; i++) {
            map1.add(i);
            map2.add(i + size);
        }

        map1.addAll(map2);
        for (int i = 0; i < 2 * size ; i++) {
            assertEquals(1, map1.getCount(i));
        }
    }
}