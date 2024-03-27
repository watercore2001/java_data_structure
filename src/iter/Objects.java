package iter;

public final class Objects {
    public static <T> void requireNonNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    public static int checkIndex(int index, int length) {
        if (index >= 0 && index < length) {
            return index;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

}
