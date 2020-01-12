package converter;

import java.util.function.Function;

public class FieldConverter {

    public  <T> String toString(T t, Function<T, String> function) {
        return function.apply(t);
    }

    public <T> T toObject(String str, Function<String, T> function) {
        return function.apply(str);
    }
}
