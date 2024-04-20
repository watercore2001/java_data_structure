package Interface;

/**
 * The user can access elements by their integer index.
 */
public interface List<E> extends Collection<E> {
    ListIterator<E> listIterator(int index);

    E get(int index);
    boolean set(int index, E var);
    boolean add(int index, E var);
    E remove(int index);

    List<E> subList(int leftIndex, int rightIndex);
}
