package collection;

import Interface.List;
import Interface.ListIterator;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Doubly-linked list implementation.
 */
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E> {
    /**
     * static class need generic type E
     * @param <E>
     */
    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

    }

    /**
     *
     * @param index index
     * @return the (non-null) Node at the specified element index
     */
    private Node<E> nodeByIndex(int index){
        if (index < (size >> 1)){
            // traverse from start
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            // traverse from end
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * Insert element e before non-null specified node
     */
    private void linkBefore(E e, Node<E> node){
        assert node != null;
        // it's ok when node.prev is null
        final Node<E> pred = node.prev;
        final Node<E> newNode = new Node<>(pred, e, node);
        node.prev = newNode;
        if (pred == null)
            // means node is the first node before insert
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    private void linkLast(E e){
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if(l == null)
            // means there is no element before insert
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    /**
     * 1. good implementation: 分开处理 prev node 和 next node,
     * 对于每个 node 再按照是否为 null 分开讨论
     * 2. bad implementation： 将情况分为 node 为第一个元素， node 为最后一个元素，node 为中间元素三种情况。
     * 错误点：这三种情况存在重合，node为第一个元素的同时也可能为最后一个元素
     */
    private E unlink(Node<E> node){
        assert node != null;
        final E element = node.item;
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;

        // first process the prev node
        if (prev==null){
            first = next;
        } else {
            prev.next = next;
            // release node's control
            node.prev = null;
        }

        if (next==null){
            last = prev;
        } else{
            next.prev = prev;
            node.next = null;
        }

        node.item = null;
        size--;
        modCount++;

        return element;
    }

    /**
     * normal class do not need generic type E
     */
    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index){
            next = (index==size) ? null : nodeByIndex(index);
            nextIndex = index;
        }


        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForCoModification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            // next will never be null because we have check haveNext()
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            checkForCoModification();
            if (!hasPrevious())
                throw new NoSuchElementException();
            nextIndex--;
            /*
             * next may be null because we only check hasPrevious()
             * but not hasNext()
             * null will be null when have iter the last item
             */
            lastReturned = next = (next == null) ? last : next.prev;
            return lastReturned.item;
        }

        @Override
        public boolean set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForCoModification();
            lastReturned.item = e;
            return true;
        }

        @Override
        public void remove() {
            checkForCoModification();
            if (lastReturned == null)
                throw new IllegalStateException();
            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);

            if(next == lastReturned)
                // lastReturned element is called by previous()
                next = lastNext;
            else
                // lastReturned element is called by next()
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        @Override
        public boolean add(E e) {
            checkForCoModification();
            lastReturned = null;
            if (next==null)
                linkLast(e);
            else
                linkBefore(e, next);
            return true;
        }

        final void checkForCoModification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }

    }

    transient int size=0;
    /**
     * Sentinel first pointer
     */
    transient Node<E> first;
    /**
     * Sentinel last pointer
     */
    transient Node<E> last;

    public LinkedList(){

    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);
    }

    @Override
    public int size() {
        return size;
    }
}
