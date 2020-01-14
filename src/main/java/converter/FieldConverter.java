package converter;

public interface FieldConverter<T> {

    String toString(T t);

    T toObject(String str);
}
