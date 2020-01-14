package converter;

public class StringConverter implements FieldConverter<String> {

    @Override
    public String toString(String s) {
        return s;
    }

    @Override
    public String toObject(String str) {
        return str;
    }
}
