package Interface;

public interface SortedMap<K, V> extends Map<K, V> {
    Comparator<? super K> comparator();

    /**
     * @return a view of Map with k1 <= k < k2.
     */
    SortedMap<K, V> subMap(K k1, K k2);

}
