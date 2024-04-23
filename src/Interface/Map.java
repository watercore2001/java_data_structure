package Interface;

/**
 * Map is not Iterable, but you can use keySet() or values() or entrySet() to iterate.
 */
public interface Map<K, V> {
    // Query

    int size();
    boolean containsKey(Object key);
    boolean containsValue(Object Value);

    /**
     * @return null if there is no mapping for key.
     */
    V get(Object key);

    // Modification

    /**
     * Add a new Entry or update an existed Entry.
     * @return the previous value associated with key,
     * or null if there was no mapping for key.
     */
    V put(K key, V value);

    /**
     * @return the previous value associated with key,
     * or null if there was no mapping for key.
     */
    V remove(Object key);


    // Iteration

    Set<K> keySet();

    Collection<V> values();

    /**
     * If the resulting Set’s iterator supports remove,
     * then THIS map will support the remove and clear operations.
     */
    Set<Map.Entry<K, V>> entrySet();

    /**
     * Q: [Why make Entry public static](<a href="https://stackoverflow.com/questions/7416241/map-entry-interface-in-java">...</a>)
     * A: Being static means you don't need an instance of Map to refer to an Entry.
     * It makes it easy to iter map by Entry.
     */
    public static interface Entry<K, V>{
        K getKey();
        V getValue();

        /**
         * @return the previous value
         */
        V setValue(V value);
    }
}
