package Abstract;

import Interface.Map;

import java.util.Iterator;
import java.util.Objects;

/**
 * Use `entrySet()` as iterator to implement
 * `containsKey()`, `containsValue`, `get`, `remove`.
 * Note that this implementation all requires linear time
 * in the size of the map; many implementations will override these method.
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    @Override
    public boolean containsKey(Object key) {
        for (Entry<K, V> kvEntry : entrySet()) {
            if (Objects.equals(key, kvEntry.getKey())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object Value) {
        for (Entry<K, V> kvEntry : entrySet()) {
            if (Objects.equals(Value, kvEntry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for (Entry<K, V> kvEntry : entrySet()) {
            if (Objects.equals(key, kvEntry.getKey())) {
                return kvEntry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }


    @Override
    public V remove(Object key) {
        Iterator<Entry<K,V>> iter = entrySet().iterator();
        Entry<K,V> correctEntry = null;

        while (correctEntry==null && iter.hasNext()) {
            Entry<K,V> kvEntry = iter.next();
            if (Objects.equals(key, kvEntry.getKey())) {
                correctEntry = kvEntry;
            }
        }

        V oldValue = null;
        if (correctEntry != null){
            oldValue = correctEntry.getValue();
            iter.remove();
        }

        return oldValue;
    }
}
