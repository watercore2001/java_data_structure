package Implement;


import Abstract.AbstractList;

import java.util.Arrays;

/**
 * 1. Introduce the Implement way basically
 * Resizable-array implementation of the List interface.
 * This class is roughly equivalent to Vector, except that it is un synchronized.
 * 2. The Speed of each Method
 * constant time: size(), isEmpty(), get(), set()
 * linear time: add(), remove()
 * 3. Itr and ListItr Implementation in AbstractList are good enough for ArrayList.
 * @param <E>
 */
public class ArrayList<E> extends AbstractList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 1. Why use Object[] but not E[]?
     * Because Java generics are implemented via type erasure
     * <a href="https://stackoverflow.com/questions/25695011/why-does-arraylist-use-object-instead-of-e-internally">...</a>
     * 2. Shared empty array instance used for empty instances to speed up.
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * Shared empty array instance used for default sized empty instance.
     * We use different inflation strategy when first element is added for two Constructor.
     */
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = new Object[0];

    /**
     * Non-private to simplify nested class access
     */
    transient Object[] elementData;
    private int size;

    public ArrayList(){
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public ArrayList(int initialCapacity){
        if (initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        } else {
            if (initialCapacity != 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
            }
            // when initialCapacity == 0
            this.elementData = EMPTY_ELEMENT_DATA;
        }
    }

    private void grow(int minCapacity){
        int oldCapacity = elementData.length;
        if (oldCapacity == 0 && elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA){
            elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        } else {
            // oldCapacity >> 1 is our preferred growth
            int newCapacity = oldCapacity + Math.max(minCapacity - oldCapacity, oldCapacity >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    // Query Operations

    @Override
    public int size() {
        return size;
    }

    // Positional Access Operations

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    @Override
    public boolean set(int index, E e) {
        rangeCheck(index);
        elementData[index] = e;
        return true;
    }

    @Override
    public boolean add(int index, E e) {
        rangeCheck(index);
        modCount++;
        if (size == elementData.length) grow();
        System.arraycopy(elementData, index, elementData, index+1, size-index);
        elementData[index] = elementData;
        size++;
        return true;
    }


    @Override
    public E remove(int index) {
        rangeCheck(index);
        modCount++;
        E result = elementData(index);
        final int newSize = size-1;
        if (size-1 > index){
            // index is not last element
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        // whatever, update size and clear the element adjacent to the array
        elementData[size = newSize] = null;
        return result;
    }
}
