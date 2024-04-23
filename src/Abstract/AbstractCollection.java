package Abstract;

import Interface.Collection;

import java.lang.StringBuilder;
import java.util.Iterator;
import java.util.Objects;

/**
 * Use `iterator.hasNext()` and `iterator.next()` to implements `self.contains(Object)` method
 * Use `iterator.remove()` to implement `self.remove(Object)`
 */
public abstract class AbstractCollection<E> implements Collection<E> {
    /**
     * Make it protected: Only called by subclass constructors
     */
    protected AbstractCollection(){}

    public abstract Iterator<E> iterator();


    @Override
    public boolean contains(Object var) {
        for (E e : this) {
            if (Objects.equals(var, e)) {
                return true;
            }
        }
        return false;
    }


    /**
     * The most important thing here is null.equals() will throw an Exception.
     * 1. Use var.equals() but not iter.next().equals()
     * because we have checked that var != var.
     * 2. Object.equals implement this
     */
    @Override
    public boolean remove(Object var){
        for (Iterator<E> iter = this.iterator(); iter.hasNext();){
            if (Objects.equals(var, iter.next())){
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
