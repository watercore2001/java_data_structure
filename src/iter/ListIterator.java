package iter;

public interface ListIterator<E> extends Iterator<E> {


    boolean hasPrevious();
    int nextIndex();
    int previousIndex();
    void set(E var);
    void add(E var);
}
