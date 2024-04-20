package collection;

import Interface.Collection;

import java.lang.StringBuilder;
import java.util.Iterator;

/**
 * This class provides a skeletal implementation of the Collection interface,
 * to minimize the effort required to implement this interface.
 */
public abstract class AbstractCollection<E> implements Collection<E> {
    /**
     * For invocation by subclass constructors
     */
    protected AbstractCollection(){}

    /**
     * Make this method abstract
     * @return an iterator over the elements contained in this collection
     */
    public abstract Iterator<E> iterator();


    @Override
    public boolean contains(Object var) {
        for (Iterator<E> iter = this.iterator(); iter.hasNext();){
            if (var==null ? iter.next() == null : var.equals(iter.next())){
                return true;
            }
        }
        return false;
    }


    /**
     * The most important thing here is null.equals() will throw an Exception.
     * So we must
     * 1. check the var is not null before call var.equals()
     * 2. can not when iter.next().equals as we do not check iter.next()!=null
     * @param var Use type Object but not type E,
     *            because We can use many type to denote type E by
     *            equals(Object o) method
     */
    @Override
    public boolean remove(Object var){
        Iterator<E> iter = this.iterator();

        if(var==null){
            while (iter.hasNext()){
                if(iter.next() == null){
                    iter.remove();
                    return true;
                }
            }
        } else {
            while (iter.hasNext()){
                if (var.equals(iter.next())){
                    iter.remove();
                    return true;
                }
            }
        }

        return false;
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
