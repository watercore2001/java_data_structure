package map;

import collection.Collection;
import collection.Set;
import iter.Comparable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * An object that maps keys to values. A map cannot contain duplicate keys;
 * each key can map to at most one value.
 * This interface takes the place of the Dictionary class, which was
 * a totally abstract class rather than an interface.
 *
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {
    // Query Operations
    int size();
    boolean isEmpty();
    boolean containsKey(Object key);
    boolean containsValue(Object Value);
    V get(Object key);

    // Modification Operations

    /**
     * Why use type K but not type Object here?
     * get() 和 remove() 使用的是 equal(Object), 不需要将 Object 类型转换为 K 类型(这不一定能成功).
     * 而 put() 需要 K 类型的对象作为 key.
     */
    V put(K key, V value);
    V remove(Object key);

    // Bulk Operations

    void putAll(Map<? extends K, ? extends V> m);

    void clear();

    // Views

    Set<K> keySet();

    Collection<V> values();

    Set<Map.Entry<K, V>> entrySet();

    interface Entry<K, V>{
        K getKey();
        V getValue();
        V setValue(V value);
        boolean equals(Object o);

        /**
         * 1.<K extends Comparable<? super K>, V>: 泛型限制。要求 K 必须是实现了 Comparable 接口的类型，
         * 并且可以比较自身类型或其超类的对象。
         * 2.Comparator<Entry<K, V>>: 返回一个 Comparator 用于比较两个 Entry
         * 3.comparingByKey(): 方法名
         * 4.(c1, c2) -> c1.getKey().compareTo(c2.getKey()): lambda 函数，利用 K.compareTo() 实现比较
         * 5.(Comparator<Entry<K, V>> & Serializable): 类型强制转换。将 Lambda 表达式
         * 转换为一个具有 Serializable 接口的 Comparator 对象。
         */
        static <K extends Comparable<? super K>, V> Comparator<Entry<K, V>> comparingByKey(){
            return (Comparator<Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getKey().compareTo(c2.getKey());
        }
    }

    // Comparison and  hashing
    boolean equals(Object o);
    int hashCode();



}
