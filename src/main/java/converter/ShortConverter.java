package converter;

public class ShortConverter implements FieldConverter<Short> {
    @Override
    public String toString(Short aShort) {
        return aShort.toString();
    }

    @Override
    public Short toObject(String str) {
        return Short.parseShort(str);
    }
}
