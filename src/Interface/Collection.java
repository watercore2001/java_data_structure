package Interface;

/**
 * Q: In Collection Interface, why .contains(Object), .add(E) and .remove(Object)
 * have different type of parameter?
 *
 * A: Because only in .add(E) method, we need to convert the input object to type E and store it,
 * for the other two methods, we only use .equals(Object) to compare if the input
 * object equals an element in the Collection.
 */
public interface Collection<E> extends Iterable<E> {
    int size();

    boolean contains(Object var);

    boolean add(E var);

    boolean remove(Object var);
}
