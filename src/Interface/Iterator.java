package Interface;

/**
 * Q1: [Why there is not .add(Object) in Iterator Interface?](<a href="https://stackoverflow.com/questions/11196561/why-there-is-no-add-method-in-iterator-interface#:~:text=The%20sole%20purpose%20of%20an,the%20case%20of%20a%20HashSet%20">...</a>)
 *
 * Opinion1: As the Iterator makes no guarantee about the order of iteration, the .add(Object) method
 * has unclear semantics: The added Object's "position" is unknown(before current pos or after current pos).
 * As the ListIterator guarantee the order of the iteration, we can provide the .add(Object) method
 *
 * Opinion2: The behavior of an .add(Object) method is not well-defined in all cases.
 * And remove() method is more useful than .add(Object) method.
 *
 * Opinion3: .add(Object) method in the Iterator will have chance to write infinite
 * loop. But I think this not make sense, because ListIterator provide .add(Object) method.
 *
 * Q2: Why there is not .set(Object) in Iterator Interface?
 *
 * A: It's not useful
 */
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove();
}
