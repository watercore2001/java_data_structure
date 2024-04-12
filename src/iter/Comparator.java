package iter;

public interface Comparator<E>{
    /**
     * @return
     * a positive value if X > Y
     * a negative value if X < Y
     * 0 if X equals Y
     */
    int compare(E x, E y);

}
