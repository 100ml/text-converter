package parser;

public abstract class Parser<T> {

    public abstract T parseString(String cell);

    public abstract String toString(T t);
}
