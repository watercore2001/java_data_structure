package test;

// import java.lang.Iterable;
// import java.util.Iterator;
import iter.Iterable;
import iter.Iterator;

import org.junit.jupiter.api.Test;

public class TestIterable<E> implements Iterable<E> {

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public void main(){
        for (E i : this){

        }
    }
}
