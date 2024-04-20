package Interface;

public interface SortedSet<E> extends Set<E> {
    /**
     * Q: Why use <? super E> in Comparator?
     *
     * A: Because type E can safely convert to type ?.
     */
    Comparator<? super E>  comparator();

    /**
     * A view of all items,
     * in THIS such that e1 <= item < e2.
     */
    SortedSet<E> subSet(E e1, E e2);
}
