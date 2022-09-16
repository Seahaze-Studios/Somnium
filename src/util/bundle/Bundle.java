package util.bundle;

public class Bundle<T, E> {
    public T object;
    public E element;

    public Bundle(T object, E element) {
        this.object = object;
        this.element = element;
    }
}
