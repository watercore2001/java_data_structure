package collection;
import iter.Comparator;

public interface SortedSet<E> extends Set<E>{
    /**
     * Q: why use <? super E>
     * A: Because <? super E> can safely convert to E,
     * then call Comparator.compare(E, E)
     */
    Comparator<? super E>  comparator();

    /**
     * A view of all items, y,
     * in THIS such that X0 <= y < X1.
     */
    SortedSet<E> subSet(E x0, E x2);
}
