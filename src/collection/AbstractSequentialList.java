package collection;

import iter.ListIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class provides a skeletal implementation of the List
 * interface to minimize the effort required to implement
 * this interface backed by a "sequential access" data store (such as a linked list).
 * For random access data store (such as an array), AbstractList should be used
 * in preference to this class.
 *
 * As ListIterator is necessary to access item in SequentialList,
 * we can put real implementation of four methods in ListIterator.
 * @param <E>
 */
public abstract class AbstractSequentialList<E> extends AbstractList<E> {
    protected AbstractSequentialList(){
    }

    @Override
    public E get(int index) {
        try {
            return listIterator(index).next();
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    @Override
    public void set(int index, E e){
        try {
            listIterator(index).set(e);
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    @Override
    public void add(int index, E element) {
        try {
            listIterator(index).add(element);
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    @Override
    public E remove(int index) {
        try {
            ListIterator<E> it = listIterator(index);
            E outVal = it.next();
            it.remove();
            return outVal;
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    /**
     * The implementation in AbstractList will call add(item) for
     * item in collection2. However, each call to add() need to move the
     * ListIterator to the last of this List.
     *
     * So, we re-implement this function.
     * @param collection2 collection to be added
     * @return true if really add item
     */
    @Override
    public boolean addAll(Collection<? extends E> collection2) {
        return super.addAll(collection2);
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    public abstract ListIterator<E> listIterator(int index);
}
