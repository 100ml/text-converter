package converter;

public class IntegerConverter implements FieldConverter<Integer> {
    @Override
    public String toString(Integer integer) {
        return integer.toString();
    }

    @Override
    public Integer toObject(String str) {
        return Integer.parseInt(str);
    }
}
