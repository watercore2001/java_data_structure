package iter;

public interface Iterator<E> {
    // Modifier 'public' is redundant for interface members
    boolean hasNext();
    E next();

    /**
     * Q: Why Iterator Interface has remove() method?
     * A: 因为我们希望，当你正在使用 iterator 遍历元素时，你不能使用其他方法修改集合，因为这样
     * 可能导致当前的 iterator 无法正常工作。
     * Q: List 使用 modCount 实现了这个限制，为什么 Set 不也使用 modCount 呢？
     */
    default void remove() {throw new UnsupportedOperationException("remove");}

    default void forEachRemaining(Consumer<? super E> action){
        Objects.requireNonNull(action);

        while (this.hasNext()){
            action.accept(this.next());
        }
    }
}
