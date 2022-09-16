package util.bundle;

public abstract class Bundle<T, E> {
    public T object;
    public E element;

    public Bundle(T object, E element) {
        this.object = object;
        this.element = element;
    }
}
