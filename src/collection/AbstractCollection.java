package collection;

import iter.Iterator;
import java.lang.StringBuilder;

public abstract class AbstractCollection<E> implements Collection<E> {
    /**
     * Ensure that only extended class can use this class
     */
    protected AbstractCollection(){}

    @Override
    public abstract int size();

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public void clear() {
        Iterator<E> iter = this.iterator();
        while(iter.hasNext()){
            /*
             * Can not alter collection like this when use Iterator
             * remove(iter.next());
             */
            iter.next();
            iter.remove();
        }
    }

    @Override
    public abstract boolean add(E var);

    @Override
    public boolean addAll(Collection<? extends E> collection2) {
        boolean result = false;
        Iterator<? extends E> iter = collection2.iterator();

        while (iter.hasNext()){
            E addedItem = iter.next();
            if (this.add(addedItem)){
                result = true;
            }
        }
        return result;
    }

    /**
     * A more elegant implementation but little slower
     */
    private boolean removeV2(Object var){
        for (Iterator<E> iter = this.iterator(); iter.hasNext();){
            if (var==null ? iter.next() == null : var.equals(iter.next())){
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object var){
        Iterator<E> iter = this.iterator();
        /*
        The most important thing here is null.equals() will throw an Exception.
        So we must
        1. check the var is not null before call var.equals()
        2. can not when iter.next().equals as we do not check iter.next()!=null
         */
        if(var==null){
            while (iter.hasNext()){
                if(iter.next() == null){
                    iter.remove();
                    return true;
                }
            }
        } else {
            while (iter.hasNext()){
                if (var.equals(iter.hasNext())){
                    iter.remove();
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection2) {
        boolean result = false;
        /*
         Must iterate this but not collection,
         because we use iter to remove item in this.
         */
        Iterator<?> iter = this.iterator();

        while (iter.hasNext()){
            if (collection2.contains(iter.next())){
                iter.remove();
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean contains(Object var) {
        for (Iterator<E> iter = this.iterator(); iter.hasNext();){
            if (var==null ? iter.next() == null : var.equals(iter.next())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection2) {
        for (Iterator<?> iter = collection2.iterator(); iter.hasNext(); ){
            if (!this.contains(iter.next())) return false;
        }
        return true;
    }

    @Override
    public abstract Iterator<E> iterator();

    // Methods from Object
    @Override
    public String toString() {
        Iterator<E> iter = this.iterator();
        if (!iter.hasNext()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (iter.hasNext()) {
                E e = iter.next();
                // important: Avoid infinite Recursion
                sb.append(e == this ? "(this Collection)" : e);
                if (!iter.hasNext()) {
                    // Break loop in advance
                    sb.append(']');
                    break;
                }
                sb.append(',').append(' ');
            }
            return sb.toString();
        }
    }
}