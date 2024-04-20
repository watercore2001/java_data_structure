package Interface;

public interface Comparable<T> {
    /**
     * @return
     * a positive value if X > Y
     * a negative value if X < Y
     * 0 if X equals Y
     */
    int compareTo(T object);
}
