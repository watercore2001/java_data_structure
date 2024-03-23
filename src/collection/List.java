package collection;

import iter.ListIterator;

public interface List<E> extends Collection<E>{
    ListIterator<E> listIterator(int index);
    ListIterator<E> listIterator();

    E get(int index);
    void set(int index, E var);
    void add(int index, E var);
    E remove(int index);

    int indexOf(Object var);
    int lastIndexOf(Object var);
}
