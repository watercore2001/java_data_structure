package collection;

import iter.Iterable;

/**
 * The iterators of Collections are fail-fast:
 * if the list is structurally modified at ant time after the iterator
 * is created, in any way except through the iterator own remove() abd add()
 * methods.
 * @param <E>
 */
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
     * @return true if remove var, else false
     */
    boolean remove(Object var);
    boolean removeAll(Collection<?> collection2);
    // Use Type Object same as remove()
    boolean contains(Object var);
    boolean containsAll(Collection<?> collection2);
}
