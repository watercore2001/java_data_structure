package collection;

import iter.Iterator;
import iter.ListIterator;

/**
 *
 * @param <E>
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>{


    @Override
    public abstract int size();

    @Override
    public boolean add(E var) {
        this.add(this.size(), var);
        return true;
    }

    @Override
    public abstract Iterator<E> iterator();

    @Override
    public abstract ListIterator<E> listIterator(int index);

    @Override
    public abstract ListIterator<E> listIterator();

    @Override
    public abstract E get(int index);

    @Override
    public abstract void set(int index, E var);

    @Override
    public abstract void add(int index, E var);

    @Override
    public abstract E remove(int index);

    @Override
    public int indexOf(Object var) {
        for (ListIterator<E> iter = this.listIterator(); iter.hasNext(); ){
            if(var==null ? iter.next() == null : var.equals(iter.next())){
                return iter.previousIndex();
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object var) {
        return 0;
    }

    private

}
