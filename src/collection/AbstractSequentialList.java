package collection;

import Interface.List;
import Interface.ListIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class provides a skeletal implementation of the List
 * interface to minimize the effort required to implement
 * this interface backed by a "sequential access" data store (such as a linked list).
 */
public abstract class AbstractSequentialList<E> extends AbstractList<E> implements List<E> {

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
    public boolean set(int index, E e){
        try {
            return listIterator(index).set(e);
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    @Override
    public boolean add(int index, E element) {
        try {
            return listIterator(index).add(element);
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


    public Iterator<E> iterator() {
        return listIterator(0);
    }

    public abstract ListIterator<E> listIterator(int index);
}
