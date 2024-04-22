package Abstract;

import Interface.List;
import Interface.ListIterator;

import java.util.Iterator;

/**
 * For `sequential access` backend (such as linked list), do not implement fail-fast
 * As SequentialList must use listIterator to access element,
 * So we use listIterator to implement self in this class, wait real implementation for listIterator(and fail-fast).
 * - Use `listIterator.next()` to implement `self.get(Int)`
 * - Use `listIterator.remove()` to implement `self.remove(Int)`.
 * - Use `listIterator.set(E)` to implement `self.set(Int, E)`.
 * - Use `listIterator.add(E)` to implement `self.add(Int, E)`.
 */
public abstract class AbstractSequentialList<E> extends AbstractList<E> implements List<E> {

    protected AbstractSequentialList(){
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return listIterator(index).next();
    }

    @Override
    public boolean set(int index, E e){
        rangeCheck(index);
        listIterator(index).set(e);
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        rangeCheck(index);
        listIterator(index).add(element);
        return true;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        ListIterator<E> it = listIterator(index);
        E outVal = it.next();
        it.remove();
        return outVal;
    }


    public Iterator<E> iterator() {
        return listIterator(0);
    }

    public abstract ListIterator<E> listIterator(int index);
}
