package collection;


public interface Collection<E> extends Iterable<E> {
    int size();

    boolean contains(Object var);

    boolean add(E var);

    boolean remove(Object var);
}
