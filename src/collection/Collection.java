package collection;

import iter.Iterable;

public interface Collection<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    void clear();

    // Do not add toArray methods

    boolean add(E var);
    boolean addAll(Collection<? extends E> collection2);

    /**
     *
     * @param var Use type Object but not type E,
     *            because We can use many type to denote type E by
     *            equals(Object o) method
     * @return if remove the item
     */
    boolean remove(Object var);
    boolean removeAll(Collection<?> collection2);
    // Use Type Object same as remove()
    boolean contains(Object var);
    boolean containsAll(Collection<?> collection2);
}
