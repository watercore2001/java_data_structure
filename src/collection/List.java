package collection;

import iter.ListIterator;

/**
 * An ordered collection, where the user has precise control over where in
 * the list each is inserted. The user can access elements by their integer index,
 * and search for elements in the list.
 */
public interface List<E> extends Collection<E>{
    ListIterator<E> listIterator(int index);

    E get(int index);
    boolean set(int index, E var);
    boolean add(int index, E var);

    E remove(int index);
}
