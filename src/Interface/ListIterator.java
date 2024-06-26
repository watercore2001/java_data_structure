package Interface;

import java.util.Iterator;

/**
 * An iterator for lists that allows the programmer to traverse the list in either direction, modify
 * the list during iteration, and obtain the iterator current position in the list.
 *
 * A ListIterator has no current element: its cursor position
 * always lies between the element that would be returned by a call to previous()
 * and the element that would be returned by a call to next()
 */
public interface ListIterator<E> extends Iterator<E> {

    @Override
    boolean hasNext();
    @Override
    E next();
    boolean hasPrevious();
    E previous();


    /**
     * Replace the last item returned by next() or previous() with the specified element
     */
    void set(E e);

    /**
     * Remove the last item returned next() or previous()
     */
    @Override
    void remove();

    /**
     * Insert the specified element into the list (optional operation).
     * The element is inserted immediately before the element that
     * would be returned by next(), and after the element that would be
     * returned by previous().
     */
    void add(E e);
}
