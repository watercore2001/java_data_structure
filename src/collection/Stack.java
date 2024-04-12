package collection;

import java.util.EmptyStackException;

/**
 * The Stack class represents a last-in-first-out stack of objects.
 */
public class Stack<E> extends ArrayList<E> {
    public Stack(){

    }

    public E push(E item){
        add(item);
        return item;
    }

    public E pop(){
        E obj = peek();
        remove(size()-1);
        return obj;
    }

    public E peek(){
        if (size() == 0)
            throw new EmptyStackException();
        return get(size()-1);
    }

}
