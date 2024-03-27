package iter;

public interface Iterator<E> {

    boolean hasNext();

    /**
     * Returns the next element in ths iteration
     * @return
     */
    E next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation). This method can be called
     * only once per cal to next()
     */
    default void remove() {throw new UnsupportedOperationException("remove");}
}
