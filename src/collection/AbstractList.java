package collection;

import iter.Iterator;
import iter.ListIterator;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Implement fail-fast Iterator by modCount
 * @param <E>
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>{
    /**
     * The number of times this list has been structurally modified.
     * This field is used by the iterator and list iterator implementation.
     */
    protected transient int modCount = 0;

    protected void rangeCheck(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(index);
    }

    protected AbstractList() {}

    // Query Operation

    private class Itr implements Iterator<E> {
        /**
         * Index of element to be returned by subsequent call to next
         */
        int cursor = 0;
        /**
         * Index of element returned by most recent call to next or
         * previous. Reset to -1 if this element is deleted by a call
         * to remove.
         */
        int lastReturned = -1;
        int expectedModCount = AbstractList.this.modCount;


        @Override
        public boolean hasNext() {
            return this.cursor != size();
        }

        @Override
        public E next() {
            this.checkModification();
            try {
                int i = this.cursor;
                E next = AbstractList.this.get(i);
                this.lastReturned = i;
                this.cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException ex){
                this.checkModification();
                throw new NoSuchElementException(ex);
            }
        }

        @Override
        public void remove() {
            if(this.lastReturned < 0){
                throw new IllegalStateException();
            } else {
                this.checkModification();
                try {
                    AbstractList.this.remove(this.lastReturned);
                    // ensure cursor points to the same element
                    if (this.lastReturned < this.cursor){
                        --this.cursor;
                    }
                    this.lastReturned = -1;
                    this.expectedModCount = AbstractList.this.modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }
        }


        protected void checkModification(){
            if (this.expectedModCount != AbstractList.this.modCount){
                throw new ConcurrentModificationException();
            }
        }

    }

    /**
     * The implementation relies on the backing list's
     * get(int) and remove(int) methods.
     * Try to override if these two methods are slow.
     * @return Iterator
     */
    @Override
    public Iterator<E> iterator(){return new Itr();}

    private class ListItr extends Itr implements ListIterator<E>  {
        public ListItr(int index){
            super();
            this.cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        @Override
        public E previous() {
            this.checkModification();
            try {
                int i = this.cursor - 1;
                E previous = AbstractList.this.get(i);
                // call next() after previous, will return the same element as last call
                this.lastReturned = this.cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e){
                this.checkModification();
                throw new NoSuchElementException(e);
            }
        }

        @Override
        public int nextIndex() {
            return this.cursor;
        }

        @Override
        public int previousIndex() {
            return this.cursor - 1;
        }

        @Override
        public void set(E e) {
            if (this.lastReturned < 0) {
                throw new IllegalStateException();
            } else {
                this.checkModification();

                try {
                    AbstractList.this.set(this.lastReturned, e);
                    this.expectedModCount = AbstractList.this.modCount;
                } catch (IndexOutOfBoundsException ex) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public void add(E e) {
            this.checkModification();
            try {
                int i = this.cursor;
                AbstractList.this.add(i, e);
                this.lastReturned = -1;
                this.cursor = i+  1;
                this.expectedModCount = AbstractList.this.modCount;
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * The implementation relies on the backing list's
     * get(int), set(int, E), add(int, E) and remove(int) methods.
     * Try to override if these four methods are slow.
     * @param index started position
     * @return ListIterator
     */
    @Override
    public ListIterator<E> listIterator(int index){
        rangeCheck(index);
        return new ListItr(index);
    }

    @Override
    public ListIterator<E> listIterator() {return this.listIterator(0);}

    @Override
    public int indexOf(Object o) {
        for (ListIterator<E> iter = this.listIterator(); iter.hasNext(); ){
            if(o==null ? iter.next() == null : o.equals(iter.next())){
                return iter.previousIndex();
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (ListIterator<E> iter = this.listIterator(this.size()); iter.hasPrevious(); ){
            if(o==null ? iter.previous() == null : o.equals(iter.previous())){
                // nextIndex() always== preIndex()+1
                return iter.nextIndex();
            }
        }
        return -1;
    }


    // Modification Operation

    @Override
    public boolean add(E var) {
        this.add(this.size(), var);
        return true;
    }

    /**
     * Use ListIterator to remove item but not Iterator.
     * If ListIterator.remove() requires linear time with size,
     * this implementation requires quadratic time.
     * @param fromIndex index of first element to be removed
     * @param toIndex index after last element to be removed
     */
    protected void removeRange(int fromIndex, int toIndex){
        ListIterator<E> it = listIterator(fromIndex);
        for (int i=0, n=toIndex-fromIndex; i<n; i++){
            it.next();
            it.remove();
        }
    }

    /**
     * Use removeRange() to implement has two difference with clear() implementation in AbstractList
     * 1. Use ListIterator rather than Iterator, it may be faster.
     * 2. Do not use iter.hasNext() to determine whether to stop.
     */
    @Override
    public void clear() {
        removeRange(0, size());
    }
}
