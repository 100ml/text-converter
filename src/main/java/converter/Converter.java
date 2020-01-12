package converter;

import java.util.List;

public abstract class Converter<T> {

    public abstract List<T> convert(String csvStr);
}
