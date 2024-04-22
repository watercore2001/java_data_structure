package Abstract;

import Interface.List;
import Interface.ListIterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * For `Random Access` backend (such as array), implement fail-fast
 * As Random Access List is better directly use index to access element,
 * So we use self to implement listIterator(and fail-fast) in this class, wait real implementation for self.
 * - Use `self.get(Int)` to implement `listIterator.next()` and `listIterator.previous()`.
 * - Use `self.remove(Int)` to implement `listIterator.remove()`.
 * - Use `self.set(Int, E)` to implement `listIterator.set(E)`.
 * - Use `self.add(Int, E)` to implement `listIterator.add(E)`.
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
    /**
     * The number of times this list has been structurally modified.
     * This field is used by the iterator and list iterator implementation.
     */
    protected transient int modCount = 0;

    protected AbstractList() {}

    protected void rangeCheck(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(index);
    }

    private class ListItr implements ListIterator<E>  {
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

        public ListItr(int index){
            this.cursor = index;
        }

        protected void checkModification(){
            if (this.expectedModCount != AbstractList.this.modCount){
                throw new ConcurrentModificationException();
            }
        }

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
                this.cursor = i+1;
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
    public Iterator<E> iterator(){return listIterator(0);}

    @Override
    public boolean add(E var) {return add(size(), var);};
}
