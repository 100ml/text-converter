package parser;

public interface FieldParser<T> {

    String toString(T t);

    T toObject(String str);
}
