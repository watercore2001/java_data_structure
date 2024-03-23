package iter;


public final class Objects {
    public static <T> void requireNonNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }
}
