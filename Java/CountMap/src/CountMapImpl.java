import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private Map<T, Integer> map;

    CountMapImpl() {
        map = new HashMap();
    }

    @Override
    public void add(T o) {
        Integer count = 1;
        if (map.containsKey(o)){
            count += map.get(o);
        }
        map.put(o, count);
    }

    @Override
    public int getCount(T o) {
        if (map.containsKey(o)){
            return map.get(o);
        }
        return 0;
    }

    @Override
    public int remove(T o) {
        if (map.containsKey(o)) {
            return map.remove(o);
        }
        return 0;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        Map<T, Integer> src = source.toMap();
        for (T key : src.keySet()) {
            map.put(key, getCount(key) + src.get(key));
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(map);
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        for (T key: map.keySet()) {
            destination.put(key, map.get(key));
        }
    }
}