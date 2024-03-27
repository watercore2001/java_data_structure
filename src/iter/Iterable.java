package iter;

public interface Iterable<T> {
    Iterator<T> iterator();

    /**
     * In official Iterator, we can use syntax like for(item: collection) if we implement
     * this method. But for our-define Iterator, this syntax still does not work.
     * @param action The type of action is Consumer<? super T>, which means
     *               although this Action is Initiated to accept a Superclass of T,
     *               we still allow it to accept object of Type T.
     *               Good: Relax the constraints for action object.
     *               Bad: This may result in an error,
     *               but that is not something this function should be concerned about.
     *
     */
    default void forEach(Consumer<? super T> action){
        Objects.requireNonNull(action);

        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()){
            T t = iterator.next();
            action.accept(t);
        }
    }
}
