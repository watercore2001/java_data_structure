package collection;

import iter.ListIterator;

/**
 * An ordered collection, where the user has precise control over where in
 * the list each is inserted. The user can access elements by their integer index,
 * and search for elements in the list.
 * @param <E>
 */
public interface List<E> extends Collection<E>{
    ListIterator<E> listIterator(int index);
    ListIterator<E> listIterator();

    E get(int index);
    void set(int index, E var);
    void add(int index, E var);
    E remove(int index);

    int indexOf(Object o);
    int lastIndexOf(Object o);
}
